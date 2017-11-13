package cn.itheima.web.action.stat;

import cn.itheima.dao.common.SqlDao;
import cn.itheima.service.IContractProductService;
import cn.itheima.web.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author huxianguang
 * @create 2017-11-12-下午8:38
 **/
@Controller
@Scope("prototype")
public class StatChartAction extends BaseAction {

    @Autowired
    private SqlDao sqlDao;

    /**
     * [
     * {
     * "country": "Lithuania",
     * "value": 260
     * },
     * 生产厂家销售情况
     *
     * @return
     */
    public String factorysale() {

        //SELECT FACTORY_ID,sum(AMOUNT) FROM CONTRACT_PRODUCT_C GROUP BY FACTORY_ID;
        String sql = "SELECT FACTORY_ID,sum(AMOUNT) FROM CONTRACT_PRODUCT_C GROUP BY FACTORY_ID";
        List list = sqlDao.executeSQL(sql);
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append("{'厂家': '" + list.get(i) + "','金额': '" + list.get(++i) + "'},");
        }
        //截取最后一个逗号i
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        put("chartData", sb.toString());
        return "factorysale";
    }


    /**
     * select * FROM (
     * SELECT
     * FACTORY_ID,
     * sum(CNUMBER)
     * FROM CONTRACT_PRODUCT_C
     * GROUP BY FACTORY_ID
     * ORDER BY sum(CNUMBER) DESC
     * ) WHERE  ROWNUM <= 10;
     * <p>
     * <p>
     * <p>
     * {
     * "country": "Czech Republic",
     * "litres": 156.9,
     * "short": "CZ"
     * },
     * 产品销量排行榜
     *
     * @return
     */
    public String productsale() {
        String sql = "select * FROM (SELECT FACTORY_ID,sum(CNUMBER) FROM CONTRACT_PRODUCT_C GROUP BY FACTORY_ID ORDER BY sum(CNUMBER) DESC) WHERE  ROWNUM <= 10";
        List list = sqlDao.executeSQL(sql);
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append("{'产品': '" + list.get(i) + "','销量': '" + list.get(++i) + "','名称':'aa'},");
        }
        //截取最后一个逗号i
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        put("chartData", sb.toString());
        return "productsale";
    }
}
