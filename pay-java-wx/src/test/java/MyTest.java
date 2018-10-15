import com.egzosn.pay.wx.bean.MchApplyInfo;
import com.egzosn.pay.wx.bean.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

/**
 * @author liutf
 */
public class MyTest {

    /**
     * 扩展xstream，使其支持CDATA块
     */
    private XStream xstream = new XStream(new XppDriver(new NoNameCoder()) {

        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = false;

                @Override
                @SuppressWarnings("rawtypes")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                public String encodeNode(String name) {
                    return name;
                }


                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    private XStream inclueUnderlineXstream = new XStream(new DomDriver(null,new XmlFriendlyNameCoder("_-", "_")));

    public XStream getXstreamInclueUnderline() {
        return inclueUnderlineXstream;
    }
    public XStream xstream() {
        return xstream;
    }


    public static void main(String[] args) {
        User user = new User("lanweihong", "lwhhhp@gmail.com");

//        //创建解析XML对象
//        XStream xStream = new XStream();
//        xStream.processAnnotations(User.class);
//        //设置别名, 默认会输出全路径
////        xStream.alias("xml", User.class);
//        //转为xml
//        String xml = xStream.toXML(user);

        MyTest myTest = new MyTest();
        XStream xstream = myTest.xstream;
        xstream.processAnnotations(User.class);
        xstream.processAnnotations(MchApplyInfo.class);
        String xml = xstream.toXML(user);
        System.out.println(xml);

        MchApplyInfo applyInfo = new MchApplyInfo()
                .setVersion("2.0")
                .setCert_sn("5663476TEREGD45FH63GDFHFG657FCHBFG")
                .setMch_id("123")
                .setNonce_str("11")
                .setSign_type("")
                .setSign("")
                .setBusiness_code("")
                .setId_card_copy("")
                .setId_card_national("")
                .setId_card_name("")
                .setId_card_number("")
                .setId_card_valid_time("")
                .setAccount_name("")
                .setAccount_bank("")
                .setBank_address_code("")
                .setBank_name("")
                .setAccount_number("")
                ;
        System.out.println(xstream.toXML(applyInfo));

    }
}
