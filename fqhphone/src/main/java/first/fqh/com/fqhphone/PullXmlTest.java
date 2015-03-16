package first.fqh.com.fqhphone;

import android.os.Environment;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by el on 2015/3/16.
 */
public class PullXmlTest {


    /**
     * <?xml version="1.0" encoding="UTF-8"/>
     * <Persons>
     * <person id="1">
     * <name>lxl</name>
     * <age>21</age>
     * <score>9</score>
     * </person>
     * <person id="2">
     * <name>dw</name>
     * <age>24</age>
     * <score>6.5</score>
     * </person>
     * <person id="3">
     * <name>ywx</name>
     * <age>25</age>
     * <score>7</score>
     * </person>
     * <person id="4">
     * <name>zunhua</name>
     * <age>22</age>
     * <score>5</score>
     * </person>
     * <person id="5">
     * <name>sgz</name>
     * <age>26</age>
     * <score>6</score>
     * </person>
     * <person id="6">
     * <name>zx</name>
     * <age>26</age>
     * <score>9.5</score>
     * </person>
     * <person id="7">
     * <name>wxj</name>
     * <age>28</age>
     * <score>8.5</score>
     * </person>
     * </Persons>
     *
     * @param is
     * @return
     */
    public static List<Object> jieXi(InputStream is) {
        List<Object> list = null;
        Object obj = null;
        XmlPullParser pullParser = Xml.newPullParser();
        try {
            pullParser.setInput(is, "UTF-8");
            int enventType = pullParser.getEventType();

            while (enventType != XmlPullParser.END_DOCUMENT) {
                switch (enventType) {
                    case XmlPullParser.START_DOCUMENT:
                        //读取xml头部<xml version="1.0" encoding="UTF-8"/>
                        break;
                    case XmlPullParser.START_TAG:
                        //初始化Object对象
                        obj = new Object();
                        if ("节点名称Xxx".equals(pullParser.getName())) {
                            //obj.setXxx( pullParser.getText());
                            String id = pullParser.getAttributeValue(0);

                        } else if (("节点名称YYY".equals(pullParser.getName()))) {
                            //obj.setYYY( pullParser.getText());
                        }
                        //...
                        break;
                    case XmlPullParser.END_TAG:
                        list.add(obj);
                        obj = null;
                        break;
                }
            }
            enventType = pullParser.next();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }


    public static void createXml(List<Object> list) throws Exception {
        FileOutputStream fs = new FileOutputStream(Environment.getExternalStorageDirectory() + "person.xml");

        XmlSerializer sx = Xml.newSerializer();
        sx.setOutput(fs,"UTF-8");
        sx.startDocument("UTF-8", true);//true 代表文件可以单独存在
        sx.startTag(null, "Persons");

        for (Object obj : list) {
            sx.startTag(null, "Person").attribute(null, "id", "obj.getId()");
                sx.startTag(null, "name").text("obj.getName").endTag(null, "name");
                sx.startTag(null, "age").text("obj.getAge").endTag(null, "age");
                sx.startTag(null, "score").text("obj.getScore").endTag(null, "score");
            sx.endTag(null, "Person");
        }
        sx.endTag(null, "Persions");
        sx.endDocument();
        fs.flush();
        fs.close();
    }

}
