/**

 @Name：layuiAdmin 设置
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License: LPPL
    
 */
 
layui.define(['form', 'upload'], function(exports){
  var $ = layui.$
  ,layer = layui.layer
  ,laytpl = layui.laytpl
  ,setter = layui.setter
  ,view = layui.view
  ,admin = layui.admin
  ,form = layui.form
  ,upload = layui.upload;

  var $body = $('body');
  
  form.render();
  
  //自定义验证
  form.verify({
    name: function(value, item){ //value：表单的值、item：表单的DOM对象
      if(value == ""){
    	  return "姓名不能为空";
      }
      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
        return '姓名不能有特殊字符';
      }
      if(/(^\_)|(\__)|(\_+$)/.test(value)){
        return '姓名首尾不能出现下划线\'_\'';
      }
      if(/^\d+\d+\d$/.test(value)){
        return '姓名不能全为数字';
      }
    }
    
    //我们既支持上述函数式的方式，也支持下述数组的形式
    //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
    ,pass: [
         /[a-zA-Z0-9]{6,12}/,
		'登录密码必须6到12位数字或字母组成！'
    ]
    
    //确认密码
    ,repass: function(value){
      if(value !== $('#LAY_password').val()){
        return '两次密码输入不一致';
      }
    }
  //确认密码
    ,qq: function(value){
      if(value != "" && !/\d{5,}/.test(value)){
        return 'qq必须为5位以上纯数字';
      }
    }
    ,uemail: function(value){
        if(value != "" && !/.*@.*/.test(value)){
          return '邮箱格式不正确';
        }
      }
  });
  
  //网站设置
  form.on('submit(set_website)', function(obj){
    layer.msg(JSON.stringify(obj.field));
   
    return false;
  });
  
  //邮件服务
  form.on('submit(set_system_email)', function(obj){
    layer.msg(JSON.stringify(obj.field));
    
    //提交修改
    /*
    admin.req({
      url: ''
      ,data: obj.field
      ,success: function(){
        
      }
    });
    */
    return false;
  });
  
  
  //设置我的资料
  form.on('submit(setmyinfo)', function(obj){
//    layer.msg(JSON.stringify(obj.field));
    
    var index = layer.load(2);
    var successCode = setter.response.statusCode.ok;
     //提交修改
     admin.req({
       url: '/user/updateUser.do'
       ,data: JSON.stringify(obj.field)
       ,success: function(res){
    	   	if(res.code == successCode){
    	   		layer.msg(res.msg, {icon: 1});
    	   	}else{
    	   		//layer.msg(res.msg, {icon: 5});
    	   	}
   			layer.close(index);
   			
       }
     });
    return false;
  });

  //上传头像
  var avatarSrc = $('#LAY_avatarSrc');
  upload.render({
    url: '/api/upload/'
    ,elem: '#LAY_avatarUpload'
    ,done: function(res){
      if(res.status == 0){
        avatarSrc.val(res.url);
      } else {
        layer.msg(res.msg, {icon: 5});
      }
    }
  });
  
  //查看头像
  admin.events.avartatPreview = function(othis){
    var src = avatarSrc.val();
    layer.photos({
      photos: {
        "title": "查看头像" //相册标题
        ,"data": [{
          "src": src //原图地址
        }]
      }
      ,shade: 0.01
      ,closeBtn: 1
      ,anim: 5
    });
  };
  
  
  //设置密码
  form.on('submit(setmypass)', function(obj){
//    layer.msg(JSON.stringify(obj.field));
	  var successCode = setter.response.statusCode.ok;
	  var index = layer.load(2);
     //提交修改
     admin.req({
       url: '/user/updatePwd.do'
       ,data: JSON.stringify(obj.field)
       ,success: function(res){
    	   layer.close(index);
    	   if(res.code == successCode){
   	   		    layer.msg(res.msg, {icon: 1});
   	   		    form.val("LAY-update-pwd",{
   	   		    	password:"",
   	   		    	newpassword:""
   	   		    })
	   	   }else{
	   	   		//layer.msg(res.msg, {icon: 5});
	   	   }
       }
     });
    return false;
  });
  
  //对外暴露的接口
  exports('set', {});
});