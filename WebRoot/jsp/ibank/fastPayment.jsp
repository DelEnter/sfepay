<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<%@taglib prefix="s" uri="/struts-tags"%>
	<head>
		<TITLE>Ecpss - 快捷支付通道</TITLE>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<STYLE type=text/css>
.12 {
	FONT-SIZE: 12px;
	COLOR: #4d5b82;
	LINE-HEIGHT: 160%
}

.12wirte {
	FONT-SIZE: 12px;
	COLOR: #ffffff
}
</STYLE>

	<script src="script/public.js" type="text/javascript"></script>
		<SCRIPT type=text/javascript>
        function toCN() {
            //            document.getElementById("html_title").innerText="支付递交页面";
            document.title = "快捷支付页";
            if (document.getElementById("pay_1") != null) document.getElementById("pay_1").innerText = "商户名：";
            if (document.getElementById("pay_3") != null)document.getElementById("pay_3").innerText = "支付金额：";
            if (document.getElementById("pay_4") != null)document.getElementById("pay_4").innerText = "订单号：";
            if (document.getElementById("pay_5") != null)document.getElementById("pay_5").innerText = "币种：";
            if (document.getElementById("pay_submit") != null)document.getElementById("pay_submit").innerText = "开始支付";
            document.getElementById("en").src='https://www.xingbill.com/images/english-up.jpg';
            document.getElementById("cn").src='https://www.xingbill.com/images/chianese-down.jpg'


        }
        function toEN() {
            document.title = "payment";
            if (document.getElementById("pay_1") != null)document.getElementById("pay_1").innerText = "Merchant: ";
//            if (document.getElementById("pay_1") != null) document.getElementById("pay_1_1").innerText = "Merchant Account for Test";
            if (document.getElementById("pay_3") != null)document.getElementById("pay_3").innerText = "Payment Amount:";
            if (document.getElementById("pay_4") != null)document.getElementById("pay_4").innerText = "Orders Num:";
            if (document.getElementById("pay_5") != null)document.getElementById("pay_5").innerText = "Currency:";
            if (document.getElementById("pay_submit") != null)document.getElementById("pay_submit").innerText = "Payment";
            document.getElementById("en").src='https://www.xingbill.com/images/english-down.jpg';
            document.getElementById("cn").src='https://www.xingbill.com/images/chianese-upjpg.jpg';

        }
        
    </SCRIPT>
		<META content="MSHTML 6.00.6000.16850" name=GENERATOR>
	</HEAD>
	<BODY onload=toEN()>
		<s:form action="http://localhost:8080/xingbill/fastPaysubmit" id="form1" theme="simple" method="post">
			<TABLE cellSpacing=0 cellPadding=0 width=646 align=center border=0>
				<TBODY>
					<TR>
						<TD vAlign=top height=174>
							<TABLE height=140 cellSpacing=0 cellPadding=0 width="100%"
								border=0>
								<TBODY>
									<TR>
									
										<TD vAlign=bottom height=13
											height=140>
											<TABLE height=32 cellSpacing=0 cellPadding=0 width="100%"
												border=0>
												<TBODY>
													<TR>
														<TD width="48%">
															<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
																<TBODY>
																	<TR>
																		<TD width="49%">
																			&nbsp;
																		</TD>
																		<TD class=12 width="39%">
																			<a href="https://www.xingbill.com" target="_blank"><STRONG></STRONG></a>
																		</TD>
																		<TD width="12%">
																			&nbsp;
																		</TD>
																	</TR>
																</TBODY>
															</TABLE>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD>
											<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
												<TBODY>
													<TR>
														<TD width=5
															height=213>
														</TD>
														<TD>
															<TABLE cellSpacing=0 cellPadding=0 width=570 align=center
																border=0>
																<TBODY>
																	<TR>
																		<TD>
																			<IMG height=12
																				src="https://www.xingbill.com/images/top-inside.jpg"
																				width=570>
																		</TD>
																	</TR>
																	<TR>
																		<TD>
																			<TABLE cellSpacing=0 cellPadding=0 width="100%"
																				border=0>
																				<TBODY>
																					<TR>
																						<TD>
																							<TABLE cellSpacing=0 cellPadding=0 width=278
																								align=center border=0>
																								<TBODY>
																									<TR>
																										<TD vAlign=top >
																											<TABLE cellSpacing=0 cellPadding=0 width=268
																												border=0>
																												<TBODY>
																													<TR>
																														<TD>
																															<IMG height=14
																																src="https://www.xingbill.com/images/top_little.jpg"
																																width=268>
																														</TD>
																													</TR>
																													<TR>
																														<TD vAlign=top style="background-color: #FFFFFF" height=82>
																															<TABLE cellSpacing=0 cellPadding=0
																																width="92%" align=center border=0>
																																<TBODY>
																																	<TR>
																																		<TD style="background-color: #ffffff" width=9>
																																		</TD>
																																		<TD style="background-color: #ffffff">
																																			<TABLE cellSpacing=0 cellPadding=0
																																				width="96%" border=0>
																																				<TBODY>
																																					<TR>
																																						<TD style="FONT-SIZE: 12px;background-color: #ffffff"
																																							width="95%">
																																							<STRONG id=pay_1>Merchant：
																																							</STRONG><STRONG id=pay_1_1> <s:property
																																									value="MerNo" /> <s:hidden
																																									name="MerNo"></s:hidden>
																																				<s:hidden name="Language" value="en"/>					
																																				<s:hidden name="ReturnURL" />	
																																				<s:hidden name="MD5key" />	
																																				<s:hidden name="tradeAdd" />	
																																								
																																									 </STRONG>
																																						</TD>
																																					</TR>
																																				</TBODY>
																																			</TABLE>
																																		</TD>
																																	</TR>
																																</TBODY>
																															</TABLE>
																															<TABLE cellSpacing=0 cellPadding=0
																																width="83%" align=center border=0>
																																<TBODY>
																																	<TR>
																																		<TD colSpan=2 height=6></TD>
																																	</TR>
																																	<TR>
																																	<TR>
																																		<TD class=12 id=pay_3 width="51%">
																																			Payment Amount:
																																		</TD>
																																		<TD width="51%">
																																			<s:textfield name="Amount" id="amount" size="15" onkeydown="onlyMoney();"></s:textfield>
																																		</TD>
																																	</TR>
																																	<TR>
																																		<TD colSpan=2 height=6></TD>
																																	</TR>
																																	<TR>
																																		<TD class=12 id=pay_4>
																																			Orders NO.:
																																		</TD>
																																		<TD>
																																			<input type="text" name="BillNo" id="billNo"  size="15">
																																		</TD>
																																	</TR>
																																	<TR>
																																		<TD class=12 id=pay_5>
																																			Currency:
																																		</TD>
																																		<TD>
																																		<select name="Currency" id="Currency">
																																		<option value="">---</option>
																																		<option value="1">USD</option>
																																		<option value="2">EUR</option>
																																		<option value="6">JPY</option>
																																		<option value="7">AUD</option>
																																		<option value="11">CAD</option>
																																		</select>
																																		</TD>
																																	</TR>
																																	<TR>
																																		<TD colSpan=2 height=6></TD>
																																	</TR>
																																</TBODY>
																															</TABLE>
																														</TD>
																													</TR>
																													<TR>
																														<TD>
																															<IMG height=11
																																src="https://www.xingbill.com/images/bottom_little.jpg"
																																width=268>
																														</TD>
																													</TR>
																												</TBODY>
																											</TABLE>
																										</TD>
																										<TD width=10>
																											&nbsp;
																										</TD>
																										<TD vAlign=top>
																											&nbsp;
																										</TD>
																									</TR>
																								</TBODY>
																							</TABLE>
																							<TABLE cellSpacing=0 cellPadding=0 width="96%"
																								align=center border=0>
																								<TBODY>
																									<TR>
																										<TD colSpan=2 height=6></TD>
																									</TR>
																									<TR>
																									<TR>
																										<TD>
																											<DIV align=center>
																												<INPUT class=input id=pay_submit type=button
																													value=PAYMENT name="Submit11" onclick="onSubmit()"/>
																											</DIV>
																										</TD>
																									</TR>
																								</TBODY>
																							</TABLE>
																						</TD>
																					</TR>
																				</TBODY>
																			</TABLE>
																		</TD>
																	</TR>
																	<TR>
																		<TD>
																			<IMG height=10
																				src="https://www.xingbill.com/images/bottom-inside.jpg"
																				width=570>
																		</TD>
																	</TR>
																</TBODY>
															</TABLE>
															<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
																<TBODY>
																	<TR>
																		<TD>
																			<DIV align=center>
																				<IMG height=36
																					src="https://www.xingbill.com/images/tu.jpg"
																					width=340>
																			</DIV>
																		</TD>
																	</TR>
																</TBODY>
															</TABLE>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD>
											<IMG height=13
												src="https://www.xingbill.com/images/bottom.jpg"
												width=646>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
					</TR>
					<TR>
						<TD>
							&nbsp;
						</TD>
					</TR>
					<TR>
						<TD>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
		</s:form>
	</BODY>
	<script type="text/javascript">
		function onSubmit(){
        	var amount = document.getElementById("amount").value;
        	var billNo = document.getElementById("billNo").value;
        	var Currency = document.getElementById("Currency").value;
        	if(amount==""){
        		alert("please input amount.")
        		return;
        	}else if(isNaN(amount)){
        		alert("please input number.")
        		return;
        	}
        	if(billNo==""){
        		alert("please input billNo.")
        		return;
        	}
        	if(Currency==""){
        		alert("please input Currency.")
        		return;
        	}
        	document.getElementById("form1").submit();
        }
       
        
</script>
</HTML>
