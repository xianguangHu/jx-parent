package cn.itheima.quartz;

import java.util.Date;

/**
 * @author huxianguang
 * @create 2017-11-12-上午9:15
 **/
public class JobText {

    public void job() {
        System.out.println("执行了调度 "+ new Date());
    }
}
