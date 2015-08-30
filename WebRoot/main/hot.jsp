<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.imageLens.js"></script>
<link rel="stylesheet" href="../js/jqzoom/jqzoom.css" type="text/css">
<script type="text/javascript">
	$(function(){
    	$(".image").imageLens();
	});
</script>
<h2>
	<span class="more"><a href="#" target="_blank">更多&gt;&gt;</a> </span>热销图书
</h2>
<div class="book_c_04">

	<!--热销图书A开始-->
	<s:iterator value="pros">
		<div class="second_d_wai">
			<div class="img">
				<a href="/dangdang/main/product.action?id=${id}" target='_blank' class='tooltip'>
				<img class="image" src="../productImages/${productPic}" border="0"/>
				</a>
			</div>
			<div class="shuming">
				<a href="/dangdang/main/product.action?id=${id}" target="_blank">${productName}</a><a href="#" target="_blank"></a>
			</div>
			<div class="price">
				<span class="del">定价：￥${fixedPrice}</span>
			</div>
			<div class="price">
				<span class="red">当当价：￥${dangPrice}</span>
			</div>
		</div>
		<div class="book_c_xy_long"></div>
	</s:iterator>
	<!--热销图书A结束-->

</div>
<div class="clear"></div>