<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>生成订单 - 当当网</title>
		<link href="../css/login.css" rel="stylesheet" type="text/css" />
		<link href="../css/page_bottom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-1.4.min.js"></script>
		<script type="text/javascript">
			
			//表单项是否通过检测
			var flag={
				"receiveName":false,
				"fullAddress":false,
				"postalCode":false,
				"phone":false,
				"mobile":false
			};
			//除掉字符串两端的空格
			function strip(str){
				var reg=/(^\s*)|(\s*$)/g;
				return str.replace(reg,'');
			}
			$(function(){
				$('#receiveName').blur(function(){
					flag.receiveName=false;
					var name=strip($(this).val());
					if(name==""){
						$("#receiveName\\.info").html("<img src='../images/wrong.gif'/>收件人姓名不能为空!");
						return;
					}
					var pattern=/^([\u4e00-\u9fa5]*[a-z0-9]*)*$/;//小写英文字母,中文,数字的正则表达式
					if(pattern.test(name)){
						flag.receiveName=true;
						$("#receiveName\\.info").html("<img src='../images/right.gif'/>可以使用!");
					}else{
						$("#receiveName\\.info").html("<img src='../images/wrong.gif'/>收件人姓名非法!");
					}
				});
			});
			$(function(){
				$('#fullAddress').blur(function(){
					flag.fullAddress=false;
					var address=strip($(this).val());
					if(address==""){
						$("#fullAddress\\.info").html("<img src='../images/wrong.gif'/>地址不能为空!");
						return;
					}
					var pattern=/^([\u4e00-\u9fa5]*[a-z0-9]*)*$/;//小写英文字母,中文,数字的正则表达式
					if(pattern.test(address)){
						flag.fullAddress=true;
						$("#fullAddress\\.info").html("<img src='../images/right.gif'/>可以使用!");
					}else{
						$("#fullAddress\\.info").html("<img src='../images/wrong.gif'/>地址非法!");
					}
				});
			});
			$(function(){
				$('#postalCode').blur(function(){
					flag.postalCode=false;
					var postalCode=strip($(this).val());
					if(postalCode==""){
						$("#postalCode\\.info").html("<img src='../images/wrong.gif'/>邮政编码不能为空!");
						return;
					}
					var pattern=/^[0-9]{6}$/;//邮政编码正则表达式
					if(pattern.test(postalCode)){
						flag.postalCode=true;
						$("#postalCode\\.info").html("<img src='../images/right.gif'/>可以使用!");
					}else{
						$("#postalCode\\.info").html("<img src='../images/wrong.gif'/>邮政编码非法!");
					}
				});
			});
			$(function(){
				$('#phone').blur(function(){
					flag.phone=false;
					var phone=strip($(this).val());
					if(phone==""){
						$("#phone\\.info").html("<img src='../images/wrong.gif'/>电话号码不能为空!");
						return;
					}
					var pattern=/^[0-9]{3,4}-[0-9]{7,8}$/;//电话号码正则表达式
					if(pattern.test(phone)){
						flag.phone=true;
						$("#phone\\.info").html("<img src='../images/right.gif'/>可以使用!");
					}else{
						$("#phone\\.info").html("<img src='../images/wrong.gif'/>电话号码非法!");
					}
				});
			});
			$(function(){
				$('#mobile').blur(function(){
					flag.mobile=false;
					var mobile=strip($(this).val());
					if(mobile==""){
						$("#mobile\\.info").html("<img src='../images/wrong.gif'/>手机号码不能为空!");
						return;
					}
					var pattern=/^[0-9]{11}$/;//电话号码正则表达式
					if(pattern.test(mobile)){
						flag.mobile=true;
						$("#mobile\\.info").html("<img src='../images/right.gif'/>可以使用!");
					}else{
						$("#mobile\\.info").html("<img src='../images/wrong.gif'/>手机号码非法!");
					}
				});
			});
			//表单提交处理
			$(function(){
				$('#f').submit(function(){
					if(flag.receiveName&&flag.fullAddress&&flag.postalCode&&flag.phone&&flag.mobile){
						return true;
					}else{
						alert("请检测表单,未通过检查!");						
						$("#receiveName\\.info").html("");
						$("#fullAddress\\.info").html("");
						$("#postalCode\\.info").html("");
						$("#phone\\.info").html("");
						$("#mobile\\.info").html("");
						return false;
					}
				});
			});
			
			$(function(){
				$('#address').change(function(){
					var address=$(this).val();
					if(address!="newaddress"){
						$.post(
							'/dangdang/order/showaddress.action',
							{'id':address},
							function(data){
								$('#receiveName').attr('value',data.receiveName);
								$('#fullAddress').attr('value',data.fullAddress);
								$('#postalCode').attr('value',data.postalCode);
								$('#phone').attr('value',data.phone);
								$('#mobile').attr('value',data.mobile);
								$('#addressId').attr('value',data.id);
								//使用以前地址,则设置表单全部通过验证
								flag.receiveName=true;
								flag.fullAddress=true;
								flag.postalCode=true;
								flag.phone=true;
								flag.mobile=true;
							}
						);
					}else{
						$('#receiveName').attr('value','');
						$('#fullAddress').attr('value','');
						$('#postalCode').attr('value','');
						$('#phone').attr('value','');
						$('#mobile').attr('value','');
						$('#addressId').attr('value','');
					}
				});
			});
		</script>
	</head>
	<body>
		<%@include file="../common/head1.jsp"%>
		<div class="login_step">
			生成订单骤: 1.确认订单 >
			<span class="red_bold"> 2.填写送货地址</span> > 3.订单成功
		</div>
		<div class="fill_message">
			<p>
				选择地址：
				<select id="address">
					<option value="newaddress">
						填写新地址
					</option>
					<s:iterator value="adds">
						<option value="${id}">
							${fullAddress}
						</option>
					</s:iterator>
				</select>
			</p>
			<form name="ctl00" method="post" action="/dangdang/order/orderOK.action" id="f">
				<input type="hidden" name="address.id" id="addressId" />

				<table class="tab_login">
					<tr>
						<td valign="top" class="w1">
							收件人姓名：
						</td>
						<td>
							<input type="text" class="text_input" name="address.receiveName"
								id="receiveName" />
							
							<div class="text_left" id="nameValidMsg">
								<p>
									请填写有效的收件人姓名
									<span id="receiveName.info" style="color:red"></span>
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							收件人详细地址：
						</td>
						<td>
							<input type="text" name="address.fullAddress" class="text_input"
								id="fullAddress" />
							<div class="text_left" id="addressValidMsg">
								<p>
									请填写有效的收件人的详细地址
									<span id="fullAddress.info" style="color:red"></span>
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							邮政编码：
						</td>
						<td>
							<input type="text" class="text_input" name="address.postalCode"
								id="postalCode" />
							<div class="text_left" id="codeValidMsg">
								<p>
									请填写有效的收件人的邮政编码
									<span id="postalCode.info" style="color:red"></span>
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							电话：
						</td>
						<td>
							<input type="text" class="text_input" name="address.phone"
								id="phone" />
							<div class="text_left" id="phoneValidMsg">
								<p>
									请填写有效的收件人的电话
									<span id="phone.info" style="color:red"></span>
								</p>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="top" class="w1">
							手机：
						</td>
						<td>
							<input type="text" class="text_input" name="address.mobile"
								id="mobile" />
							<div class="text_left" id="mobileValidMsg">
								<p>
									请填写有效的收件人的手机
									<span id="mobile.info" style="color:red"></span>
								</p>
							</div>
						</td>
					</tr>
				</table>

				<div class="login_in">

					<a href="order_info.jsp"><input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="取消" /></a>
			
				<input id="btnClientRegister" class="button_1" name="submit"
					type="submit" value="下一步" />
				</div>
			</form>
		</div>
		<%@include file="../common/foot1.jsp"%>
	</body>
</html>

