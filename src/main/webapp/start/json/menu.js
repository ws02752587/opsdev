{
  "code": 0
  ,"msg": ""
  ,"data": [{
	"title": "分支"
		,"name":"branch"
		,"icon": "layui-icon-app"
		,"jump":"branch/list"
  },{
    "title": "环境"
    ,"name":"env"
    ,"icon": "layui-icon-template"
    ,"list": [{
       "name":"deploy"
      ,"title": "部署"
      ,"jump": "env/deploy/list"
    }, {
      "name": "info"
      ,"title": "环境信息"
      ,"jump": "env/info/list"
    }]
  },{
    "title": "版本"
    ,"name":"edition"
    ,"icon": "layui-icon-component"
    ,"list": [{
       "name":"beonline"
      ,"title": "上线版本"
      ,"jump": "edition/beonline/list"
    }, {
      "name": "phone"
      ,"title": "手机版本"
      ,"jump": "edition/phone/list"
    }, {
      "name": "driver"
      ,"title": "客户端版本号"
      ,"jump": "edition/driver/list"
    }]
  },{
	"title": "小组"
	,"name":"group"
	,"icon": "layui-icon-home"
	,"jump":"group/list"
  },{
	"title": "用户"
	,"name":"user"
	,"icon": "layui-icon-user"
	,"list": [{
	  "title": "网站用户",
	  "name": "webuser",
	  "jump":"user/webuser/list"
	}]
  },{
	"title": "SVN"
		,"name":"svn"
		,"icon": "layui-icon-senior"
		,"jump":"svn/list"
  },{
	 "name": "set"
    ,"title": "设置"
    ,"icon": "layui-icon-set"
    ,"list": [{
    	"name": "info"
        ,"title": "基本资料"
        ,"jump":"set/info"
      },{
        "name": "password"
        ,"title": "修改密码"
        ,"jump":"set/password"
      }]
  }]
}