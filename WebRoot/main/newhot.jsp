<%@page contentType="text/html;charset=utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<div class="book_r_border2" id="__XinShuReMai">
	<div class="book_r_b2_1x" id="new_bang">
		<h2 class="t_xsrm">
			新书热卖榜
		</h2>
		<s:iterator value="pros">
			<div id="NewProduct_1_o_t" onmouseover="">
				<h3 class="second">
					<span class="dot_r"> </span><a href="/dangdang/main/product.action?id=${id}" target="_blank">${productName}</a>
				</h3>
			</div>
		</s:iterator>
		<h3 class="second">
			<span class="dot_r"> </span><a href="#" target="_blank">更多&gt;&gt;</a>
		</h3>
	</div>
</div>