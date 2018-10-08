$(function(){
	//验证手机号
	$("#phone").blur(function(){
		var phone = $(this).val().trim();
		var reg = /^1[34578]\d{9}$/;
		if (!reg.test(phone))
		{
			tip('手机号格式不正确', 'show');
		}
		else
		{
			tip('', 'hide');
		}
	});
	//验证用户名
	$("#username").blur(function(){
		var username = $(this).val();
		if (username.length < 2 || username.length > 16)
		{
			tip('用户名不可用', 'show');
		}
		else
		{
			tip('', 'hide');
		}
	});
	//验证密码1
	$("#password").blur(function(){
		var password = $(this).val().trim();
		var length = password.length;
		if (length<6 || length > 16)
		{
			tip('密码为6~16位字符', 'show');
		}
		else
		{
			tip('', 'hide');
		}
	});
	//验证密码2
	$("#password-confirm").blur(function(){
		var password_confirm = $(this).val();
		var password = $("#password").val();
		var length = password.length;
		if (length<6 || length > 16)
		{
			tip('请填写合法密码', 'show');
		}
		else if (password != password_confirm)
		{
			tip('密码不一致', 'show');
		}
		else
		{
			tip('', 'hide');
		}
	});
	//验证邮箱
	$("#email").blur(function(){
		var email = $(this).val();
		var reg = /^[a-z]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i;
		if (!reg.test(email))
		{
			tip('邮箱格式不正确', 'show');
		}
		else
		{
			tip('', 'hide');
		}
	});
	
});
/**
 * 
 * @param tipStr 提示内容
 * @param option 提示选项 show、hide
 */
function tip(tipStr, option){
	$("#tip").html(tipStr);
	if (option == 'show')
	{
		$("#tip").slideDown('fast');
	}
	else
	{
		$("#tip").slideUp('fast');
	}
	
}

/**
	验证手机注册是否可以提交
*/
function submitCheck(type)
{
	var verPhone = true;
	if (type == 'phone')
	{
		var phone = $('#phone').val().trim();
		var reg1 = /^1[34578]\d{9}$/;
		verPhone = reg1.test(phone);
	}
	var verEmail = true;
	var email = $('#email').val().trim();
	var reg1 = /^[a-z]([a-z0-9]*[-_]?[a-z0-9]+)*@([a-z0-9]*[-_]?[a-z0-9]+)+[\.][a-z]{2,3}([\.][a-z]{2})?$/i;
	verEmail = reg1.test(email);
	
	var username =  $('#username').val().trim();
	var reg2 = /^[0-9a-zA-Z]{6,16}$/;
	var verUsername = reg2.test(username);
	var verPassword = true;
	var password = $("#password").val();
	var length = password.length;
	if (length<6 || length > 16)
	{
		verPassword = false;
	}
	var password_confirm = $("#password-confirm").val();
	verPasswordConfirm = true;
	if (password_confirm != password)
		verPasswordConfirm = false;
	if (verEmail && verPhone && verUsername && verPassword && verPasswordConfirm)
		return true;
	tip('请先正确填写好信息', 'show');
	return false;
}
