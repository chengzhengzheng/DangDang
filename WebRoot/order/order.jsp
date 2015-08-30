<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户订单 - 当当网</title>
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			订单信息:
		</div>
		<div class="fill_message">

			<table class="tab_login">
			<s:if test="orders.size()!=0">			
				<tr>
					<td valign="top" class="w1" style="text-align: left">
						<b>订单号</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>下单时间</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>订单状态</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>订单总额</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>收货人</b>
					</td>
					<td valign="top" class="w1" style="text-align: left">
						<b>收货地址</b>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td valign="top" class="w1" style="text-align: left">
						<b>暂无订单记录</b>
					</td>
				</tr>
			</s:else>
			
				<!-- 订单开始 -->
				<s:iterator value="orders" var="o">	
					<tr>
						<td valign="top">
							${o.id}
						</td>
						<td valign="top">
							<s:date name="orderDt" format="yyyy年MM月dd日"/>
						</td>
						<td valign="top">
							<s:if test="status==1">等待付款</s:if>
							<s:else>已成交</s:else>
						</td>
						<td valign="top">
							${o.totalPrice}
						</td>
						<td valign="top">
							${o.address.receiveName}
						</td>
						<td valign="top">
							${o.address.fullAddress}
						</td>
					</tr>
				</s:iterator>	
				<!-- 订单结束 -->
				
			</table>
			<br />
			<br />
			<br />
			<div class="login_in">
				<a href="/dangdang/cart/cartlist.action"><input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="返回" /></a>
			
				<a href="/dangdang/order/address.action"><input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="下一步" /></a>
		
			</div>

		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

