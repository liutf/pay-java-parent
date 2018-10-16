import com.egzosn.pay.common.util.sign.SignUtils;
import com.egzosn.pay.wx.bean.MchApplyInfo;
import com.egzosn.pay.wx.bean.SignTestBean;
import com.egzosn.pay.wx.bean.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static com.egzosn.pay.common.util.sign.SignUtils.parameterText;

/**
 * @author liutf
 */
public class MyTest {

    @Test
    public void asdd() throws Exception {

        SignTestBean signTestBean = new SignTestBean()
                .setAppid("wxd930ea5d5a258f4f")
                .setMch_id("10000100")
                .setDevice_info("1000")
                .setBody("test")
                .setNonce_str("ibuaiVcKdpRxkhJA");

        Map<String, String> paramMap = BeanUtils.describe(signTestBean);
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key:" + key + " value:" + value);
        }
        paramMap.remove("class");

        String content = parameterText(paramMap, "&");
        System.out.println(content);

        String signMd5 = SignUtils.valueOf("MD5".toUpperCase()).createSign(content, "&key=192006250b4c09247ec02edce69f6a2d", "utf-8").toUpperCase();
        String signSha256 = SignUtils.valueOf("HMACSHA256".toUpperCase()).createSign(content, "&key=192006250b4c09247ec02edce69f6a2d", "utf-8").toUpperCase();
        Assert.assertEquals("9A0A8659F005D6984697E2CA0A9CF3B7",signMd5);
        Assert.assertEquals("6A9AE1657590FD6257D693A078E1C3E4BB6BA4DC30B23E0EE2496E54170DACD6",signSha256);

//        System.out.println(xstream.toXML(signTestBean));
    }


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


    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
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

//        MchApplyInfo applyInfo = new MchApplyInfo()
//                .setVersion("2.0")
//                .setCert_sn("5663476TEREGD45FH63GDFHFG657FCHBFG")
//                .setMch_id("1516660871")
//                .setNonce_str("11")
//                .setSign_type("")
//                .setSign("")
//                .setBusiness_code("")
//                .setId_card_copy("")
//                .setId_card_national("")
//                .setId_card_name("")
//                .setId_card_number("")
//                .setId_card_valid_time("")
//                .setAccount_name("")
//                .setAccount_bank("")
//                .setBank_address_code("")
//                .setBank_name("")
//                .setAccount_number("")
//                ;




    }
}
