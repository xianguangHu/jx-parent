<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>

    <link rel="stylesheet" href="${ctx }/components/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx }/components/zTree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx }/components/zTree/js/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="${ctx }/components/zTree/js/jquery.ztree.excheck-3.5.min.js"></script>
</head>
<script type="text/javascript">

    var zTreeObj ;
    //每种tree的格式不一样，这个设置不一样，直接去相关的例子中查找
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    //最简单:{id:1,pId:1,name:''}
    //最全面:{id:1,pId:1,name:'',checked:true|false,open：true:false}
    //动态生成数据的方式:
    //1 在进入页面之前，在toModule方法中准备数据,放入值栈，页面直接获取
    //2 进入页面之后，可以发送ajax请求，去后台获取数据
    //等页面加载完成的时候， 去使用ajax
    //以下三种方式都是表示页面加载完成
    //$(document).ready(function(){});
    //$().ready(function(){});
    //$(function(){});

    //页面加载完执行
    $(function(){
        $.ajax({
            url:'${ctx}/sysadmin/roleAction_ajaxModule.action?id=${id}',
            type:'get',
            success:initZtree,
            dataType:"text"

        });

        function initZtree(data){
            var zNodes = eval("("+data+")");
            zTreeObj = $.fn.zTree.init($("#jxTree"), setting, zNodes);
        }
    });

    function submitCheckedNodes(){
        //nodes是一个数组,里面存放的是一个个的node
        //每个node都有id,pId,name,checked,open属性
        var nodes = zTreeObj.getCheckedNodes(true);
        var str = "";
        for(var i=0;i<nodes.length;i++){
            str+=nodes[i].id+",";
        }
        //截取最后一个逗号
        str=str.substring(0,str.length-1);
        //将选中的结点放到隐藏域中
        $("#moduleIds").val(str);
    }

</script>
<body>
<form name="icform" method="post">
	<input type="hidden" name="id" value="${id}"/>
	<input type="hidden" id="moduleIds" name="moduleIds" value="" />
<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
<li id="save"><a href="#" onclick="submitCheckedNodes();formSubmit('roleAction_module','_self');this.blur();">保存</a></li>
<li id="back"><a href="#" onclick="formSubmit('roleAction_list','_self');this.blur();">返回</a></li>
</ul>
  </div>
</div>
</div>
</div>
   
<div class="textbox" id="centerTextbox">
  <div class="textbox-header">
  <div class="textbox-inner-header">
  <div class="textbox-title">
    配置 [${name}] 角色的模块  
  </div> 
  </div>
  </div>
    <div>
        <ul id="jxTree" class="ztree"></ul>
    </div>
 
 
</form>
</body>
</html>

