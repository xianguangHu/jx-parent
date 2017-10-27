package cn.itheima.util;

import cn.itheima.common.SysContant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页辅助类
 * @author huxianguang
 * @create 2017-10-26-下午8:29
 **/
public class Page<T> {
    private int pageNo = 1;  //页码 默认是第一页
    private int pageSize = SysContant.PAGE_SIZE; //每页显示的记录数
    private int totalRecord;  //总记录数
    private int totalPage;   //总页数
    private List<T> results;  //对应的当前记录
    private Map<String,Object> params = new HashMap<String, Object>();

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        //计算总页数
        int totalPage = totalRecord%pageSize==0 ?totalRecord/pageSize : totalRecord/pageSize +1;
        setTotalPage(totalPage);
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    //页面连接
    public String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String links;

    public String getLinks() {
        String str = "";
        str+="<input type='hidden' id='pageNo' name='page.pageNo' value='1'>";
        //第1页 / 共1页  总共8条记录 每页10条记录
        str+="第"+pageNo+"页 / 共"+totalPage+"页  总共"+totalRecord+"条记录 每页"+pageSize+"条记录";
        str+="<a href='#' onclick=\"turnPage(1,'"+url+"')\">【首页】</a>";
        //[上一页]
        int before = pageNo-1<=1?1:pageNo-1;
        str+="<a href='#' onclick=\"turnPage("+before+",'"+url+"')\">[上一页]</a>";
        //[下一页]
        int after = pageNo+1>=totalPage?totalPage:pageNo+1;
        str+="<a href='#' onclick=\"turnPage("+after+",'"+url+"')\">[下一页]</a>";

        //[末页]
        str+="<a href=\"javascript:turnPage("+totalPage+",'"+url+"')\">【末页】</a>";
        return str;
    }
}
