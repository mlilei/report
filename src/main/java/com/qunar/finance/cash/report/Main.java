package com.qunar.finance.cash.report;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Description:
 * User: lilei
 * Date: 2017-11-02
 * Time: 18:00
 */
public class Main {

    private static final Gson JSON = new Gson();


    public static void main(String[] args) {

        Element element = getElement();

        List<String> titles = getTitles(element);

        List<List<String>> body = getBody(element);

        String emailBody = buildHtml(titles, body);

        System.out.println(emailBody);

    }

    private static String buildHtml(List<String> titles, List<List<String>> body) {

        StringBuilder html = new StringBuilder();
        for (String title : titles) {
            html.append(String.format(titleTemplate(), title));
        }
        html = new StringBuilder(String.format(trTemplate(), html));

        String status = "";

        for (List<String> rowList : body) {
            StringBuilder row = new StringBuilder();
            if (status.equals(rowList.get(3))) {
                for(String content:rowList){
                    row.append(String.format(contentTemplate(),content));
                }
            } else {
                status = rowList.get(3);
                row.append(String.format(statusTitleTemplate(), status));
            }
            row = new StringBuilder(String.format(trTemplate(), row));

            html.append(row);
        }


        return html.toString();
        //return String.format(cssTemplate(),html);
    }


    private static Element getElement() {
        Connection.Response response = null;
        String url = "http://pmo.corp.qunar.com/issues/?filter=22951&jql=project%20in%20(FINANCE%2C%20PAY)%20AND%20issuetype%20in%20(subTaskIssueTypes()%2C%20standardIssueTypes())%20AND%20(status%20in%20(%E9%9C%80%E6%B1%82FR%E9%80%9A%E8%BF%87%2C%20%E5%BE%85%E5%AE%9A%2C%20%E5%B7%B2%E6%8E%92%E6%9C%9F%2C%20%E5%BC%80%E5%8F%91%E4%B8%AD%2C%20%E5%BC%80%E5%8F%91%E5%AE%8C%E6%88%90%2C%20%E5%B7%B2%E6%8F%90%E6%B5%8B%2C%20%E6%B5%8B%E8%AF%95%E4%B8%AD%2C%20%E5%BE%85%E5%8F%91%E5%B8%83%2C%20%E6%9A%82%E5%81%9C%2C%20%E5%BE%85%E5%AE%A1%E6%89%B9%2C%20%E6%89%B9%E5%87%86)%20OR%20status%20%3D%20%E5%B7%B2%E5%85%B3%E9%97%AD%20AND%20%E5%AE%9E%E9%99%85%E5%8F%91%E5%B8%83%E6%97%B6%E9%97%B4%20%3E%3D%20%222017-11-01%2000%3A00%22)%20AND%20DEV%20in%20(hongyang.gao%2C%20chunlong.wang%2C%20junhua.han%2C%20hailong.wangwhl%2C%20xuyang.ma%2C%20leili.li%2C%20xingwei.bi%2C%20zhanghe.zhang)%20ORDER%20BY%20status%20DESC";

        try {
            response = Jsoup.connect(url)
                    .method(Connection.Method.POST)
                    .cookie("QN99", "7136")
                    .cookie("QunarGlobal", "192.168.31.100_-6203c463_15d3fab353b_954|1500012863066")
                    .cookie("QN601", "e7da4de151910963c2e939a3883b2385")
                    .cookie("QN48", "tc_57cd741d356cfacc_15d3fbbdfc2_e723")
                    .cookie("fid", "83b3d672-b2dd-49ed-b4d5-d71d3c9c7e74")
                    .cookie("Hm_lvt_75154a8409c0f82ecd97d538ff0ab3f3", "1500012864,1500454682,1502284329")
                    .cookie("csrfToken", "IDsXvotOdcxDqJ6ni6VyC1gzMuAp39ji")
                    .cookie("_s", "s_OJA6KGM2F4Y7GMIV47HUR76IO4")
                    .cookie("_v", "sTZbO1RZPRgVBkFSNqvzZzBnAEeZlD9ntsssic2vGhiJK3Ae111zQPcYinJCVgnfruimSAjCSG8Er1riIM9GCW480LkQvsPPzuQXztrhY6MsfRAL2OlwchH_fS1INZ6ZGfbshlJ3M2DPQiNqnsOQXxQ19ANoPthTGNLIrizeF82l")
                    .cookie("_t", "25309974")
                    .cookie("_q", "U.awfwcpv9662")
                    .cookie("QN1", "dXrgjVnKSE4GX0lJCBYXAg==")
                    .cookie("_i", "RBTjeRn6xdmQ9fcRsK_UXOYZNxUx")
                    .cookie("_vi", "JpiKnKcHC7ujo9HbcI0VpKWZppLYQ1Z-7B4bYEnCBUMPSowZH3Iy6uHbVdJsput5r2HDmKNX_IqpINZDKHredRpG-LQb7KTGdQEV2mh4dVWHbwzZycoMXYBI61UKY6HKGlnMdZcW8R-Vj60VD9gCp2BGIzsDj8QXM-JxQW5lFJDD")
                    .cookie("seraph.rememberme.cookie", "111641%3A6316c36d77f1930387a15ecbdc3be64f05c8cd01")
                    .cookie("_ga", "GA1.2.596125095.1508905063")
                    .cookie("SignOnDefault", "leili.li")
                    .cookie("JSESSIONID", "49C2FE0283B358A7A5393A1549C5F09F")
                    .cookie("atlassian.xsrf.token", "BRVN-QI7X-8VWF-JILI|97247077abee3ea9a4bb357617c06cd1fdc6ac96|lin")
                    .cookie("wooTracker", "v2kZ8NoU2xuz")
                    .execute();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Document parse = null;
        try {
            parse = response.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parse.getElementById("issuetable");
    }

    private static List<String> getTitles(Element element) {
        Elements rowHeader = element.getElementsByClass("rowHeader");

        String text = rowHeader.text();

        List<String> titles = splitToList(text);
        //System.out.println(JSON.toJson(titles));

        return titles;
    }

    private static List<List<String>> getBody(Element element) {

        Element tbody = element.getElementsByTag("tbody").get(0);

        Elements rows = tbody.getElementsByTag("tr");

        List<List<String>> body = Lists.newArrayList();
        for (int i = 0; i < rows.size(); i++) {
            String text = rows.get(i).text();

            List<String> textList = splitToList(text);
            //System.out.println(JSON.toJson(textList));
            body.add(textList);
        }


        return body;
    }

    private static List<String> splitToList(String input) {
        return Splitter.on(" ").splitToList(input);
    }


    private static String trTemplate() {
        return "<tr height=\"76\" style=\"height:57.0pt\">%s</tr>";
    }

    private static String titleTemplate() {
        return "<td height=\"40\" class=\"xl71\" width=\"77\" style=\"height: 30pt; width: 58pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-weight: 700; font-family: 宋体; vertical-align: top; border: 0.5pt solid black; text-align: center; background-color: rgb(255, 192, 0);\">%s</td>\n";
    }

    private static String statusTitleTemplate() {
        return "<td height=\"19\" class=\"xl70\" style=\"height: 14.25pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: 等线; vertical-align: middle; border: none; white-space: nowrap; background-color: yellow;\">%s</td>\n";
    }

    private static String contentTemplate() {
        return "<td class=\"xl65\" width=\"77\" style=\"border: 0.5pt solid black; width: 58pt; padding-top: 1px; padding-right: 1px; padding-left: 1px; font-size: 11pt; font-family: Arial, sans-serif; vertical-align: top;\">%s</td>";
    }

    private static String cssTemplate() {
        return "<style class=\"fox_global_style\">div.fox_html_content { line-height: 1.5; }blockquote { margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em; }p { margin-top: 0px; margin-bottom: 0px; }div.foxdiv20171103104242933893 { }div.fox_html_content { font-size: 10.5pt; font-family: 'Microsoft YaHei UI'; color: rgb(0, 0, 0); line-height: 1.5; }</style> <div><span id=\"_FoxCURSOR\"></span><br></div><blockquote style=\"margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em;\"><div><div class=\"FoxDiv20171103104242933893\" id=\"FMOriginalContent\"> <div><span></span> <div> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"1232\" style=\"border-collapse: collapse;width:928pt\"> <!--StartFragment--> <colgroup><col width=\"77\" span=\"16\" style=\"mso-width-source:userset;mso-width-alt:2464; width:58pt\"> </colgroup>\n" +
                "<tbody>%s</tbody>\n" +
                "</table></div></div><blockquote style=\"margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em;\"><div><div class=\"FoxDiv20171018113015404793\"><blockquote style=\"margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em;\"><div><div class=\"FoxDiv20171017114052581858\"><blockquote style=\"margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em;\"><div><div class=\"FoxDiv20171016121908731510\"><hr style=\"width: 210px; height: 1px;\" color=\"#b5c4df\" size=\"1\" align=\"left\"> <div><span><div style=\"MARGIN: 10px; FONT-FAMILY: verdana; FONT-SIZE: 10pt\"><p class=\"MsoNormal\" style=\"margin: 0px 0.9pt 0px 0cm; text-align: justify; font-size: 14px; font-family: Calibri, sans-serif; line-height: normal;\"><b><span style=\"font-size: 14pt; font-family: 微软雅黑, sans-serif; color: rgb(31, 73, 125);\">李磊<span lang=\"EN-US\">&nbsp;&nbsp;</span></span></b><b><span style=\"font-family: 微软雅黑, sans-serif; color: rgb(31, 73, 125);\">金融事业部</span></b><b><span lang=\"EN-US\" style=\"font-size: 12pt; font-family: 微软雅黑, sans-serif; color: rgb(31, 73, 125);\"><o:p></o:p></span></b></p><p class=\"MsoNormal\" style=\"margin: 0px 0.9pt 0px 0cm; text-align: justify; font-size: 14px; font-family: Calibri, sans-serif; line-height: normal;\"><span lang=\"EN-US\" style=\"font-size: 9pt; font-family: 微软雅黑, sans-serif; color: rgb(31, 73, 125);\">==============================================================</span><span lang=\"EN-US\" style=\"font-size: 9pt; color: rgb(31, 73, 125);\"><o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0px 0.9pt 0px 0cm; text-align: justify; font-size: 14px; font-family: Calibri, sans-serif; line-height: normal;\"><span lang=\"EN-US\" style=\"font-size: 9pt; font-family: 微软雅黑, sans-serif; color: rgb(31, 73, 125);\">Mobile: 17610065303</span></p><p class=\"MsoNormal\" style=\"margin: 0px 0.9pt 0px 0cm; text-align: justify; font-size: 14px; font-family: Calibri, sans-serif; line-height: normal;\"><span lang=\"EN-US\" style=\"font-size: 9pt; font-family: 微软雅黑, sans-serif; color: rgb(31, 73, 125);\">Add:&nbsp;</span><span style=\"font-size: 9pt; font-family: 微软雅黑, sans-serif; color: rgb(31, 73, 125);\">北京市海淀区丹棱街<span lang=\"EN-US\">3</span>号中国电子大厦<span lang=\"EN-US\">B</span>座<span lang=\"EN-US\">17</span>层，<span lang=\"EN-US\">100080</span></span></p><p class=\"MsoNormal\" style=\"margin: 0px 0.9pt 0px 0cm; text-align: justify; font-size: 14px; font-family: Calibri, sans-serif; line-height: normal;\"><span lang=\"EN-US\" style=\"font-size: 9pt; font-family: 微软雅黑, sans-serif; color: rgb(31, 73, 125);\">==============================================================<o:p></o:p></span></p><p class=\"MsoNormal\" style=\"margin: 0px 0cm; text-align: justify; font-size: 14px; font-family: Calibri, sans-serif; line-height: normal;\"><span lang=\"EN-US\" style=\"font-family: Arial, sans-serif;\"><img border=\"0\" width=\"113\" height=\"32\" id=\"图片_x0020_1\" src=\"C:\\Users\\lilei\\AppData\\Roaming\\Foxmail7\\Temp-4932-20171030113442\\Attach/1967_image001(07(11-03-10-42-42).gif\" alt=\"image011\" style=\"max-width: 100%; height: auto !important;\"></span><span lang=\"EN-US\" style=\"font-family: 微软雅黑, sans-serif; color: rgb(31, 73, 125);\">Think.Search.Travel</span><span style=\"font-family: 微软雅黑, sans-serif; color: rgb(31, 73, 125);\">！</span></p></div></span></div> </div></div></blockquote> </div></div></blockquote> </div></div></blockquote> </div></div></blockquote>\n";
    }
}
