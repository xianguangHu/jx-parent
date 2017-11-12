package cn.itheima.quartz;

import cn.itheima.domain.Contract;
import cn.itheima.service.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;

/**
 * @author huxianguang
 * @create 2017-11-12-上午9:21
 **/
public class ShipTimeJob {

    @Autowired
    private SimpleMailMessage mailMessage;
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private IContractService contractService;
    /**
     * 订单在交期前三天向厂家发送电子邮件
     */
    public void sendMail() {
        //select * from contract_c where to_char(ship_time-3,'yyyy-mm-dd') = to_char(sysdate,'yyyy-mm-dd');
        String hql = "from Contract where to_char(shipTime - 3,'yyyymmdd') = to_char(sysdate,'yyyymmdd')";
        List<Contract> contracts = contractService.find(hql, Contract.class, null);
        for (final Contract contract : contracts) {
            new Thread(new Runnable() {
                public void run() {
                    mailMessage.setSubject("船期");
                    mailMessage.setText("你的合同号为:"+contract.getContractNo()+",还有三天就要上船了");
                    mailMessage.setTo("15789762@qq.com");
                    //发送
                    mailSender.send(mailMessage);
                }
            }).start();
        }
    }
}
