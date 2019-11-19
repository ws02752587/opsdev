/**

 @Name：部署管理
 @Author：jiangjun
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */

layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,admin = layui.admin
  ,view = layui.view
  ,table = layui.table
  ,form = layui.form
  ,setter = layui.setter;
  var element = layui.element;
  var successCode = setter.response.statusCode.ok;
  var deployId = -1;
  var deployWebId = -1;
  var progress = function(i, data){
	  var progress = '<div style="height:6px;"></div><div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="component-progress-deploy'+i+'">'+
      '<div class="layui-progress-bar layui-bg-red" lay-percent="0%"></div>'+
      '</div>';
	$('table tr[data-index="'+i+'"] td[data-field="appStatus"] div').html(progress);
	var flag = true;
	var flag2 = true;
	var num = 0;
	var maxtime=60;
	element.render('progress');
	 var n = 0, timer = setInterval(function(){
		 n += Math.round(100/maxtime);
      if(n>100){
        n = 100;
        clearInterval(timer);
      }
      element.progress('component-progress-deploy'+i+'', n+'%');
      if(n == 100){
    	  checkAppServer(i ,data);
    	  flag = false;
    	  flag2 = false;
    	  return;
      }else if((flag && (n > 50 && n < 80)) || (flag2 && n > 80)){
    	  num += 1;
      	  if(num == 1){
      		  flag = false;
      	  }
      	  if(num == 2){
      		  flag2 = false;
      	  }
      	admin.req({
            url: '/deploy/checkAppServer.do'
            ,data: JSON.stringify({id:data[i].id})
            ,success: function(result){
  	      		if(result.code == successCode){
  	      			var datar = result.data;
  	      			var appStatus = datar.appStatus;
  	      			var str = "";
  	      			if(appStatus === "1"){
  	      				str = "<span class='layui-badge layui-bg-green'>正常</span>";
  	      			}else if(appStatus == "3"){
  	      				str = "<span class='layui-badge layui-bg-orange'>应用报错</span>";
  	      			}else if(appStatus == "5"){
	      				str = "<span class='layui-badge'>打包失败</span>";
	      			}else if(appStatus == "8"){
  	      				str = "<span class='layui-badge'>分支下载失败</span>";
  	      			}else if(appStatus == "9"){
  	      				str = "<span class='layui-badge'>分支更新失败</span>";
  	      			}
  	      			if(str != ''){
  	      				$('table tr[data-index="'+i+'"] td[data-field="appStatus"] div').html(str);
	  	      			clearInterval(timer);
	      				flag = false;
	      				flag2 = false;
  	      			}
  	      		}
            }
          });
      }
    }, 1000);
  }
  var progressWeb = function(i, data){
	  var progress = '<div style="height:6px;"></div><div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="component-progress-deployWeb">'+
      '<div class="layui-progress-bar layui-bg-red" lay-percent="0%"></div>'+
      '</div>';
	$('table tr[data-index="'+i+'"] td[data-field="webStatus"] div').html(progress);
	element.render('progress');
	var maxtime=30;
	var flag = true;
	 var n = 0, timer = setInterval(function(){
		 n += Math.round(100/maxtime);
      if(n > 100){
    	  n = 100;
        clearInterval(timer);
        checkWebServer(i ,data);
      }
      element.progress('component-progress-deployWeb', n+'%');
      if(flag && n > 50){
    	  flag = false;
    	  admin.req({
              url: '/deploy/checkWebServer.do'
              ,data: JSON.stringify({id:data[i].id})
              ,success: function(result){
    	      		if(result.code == successCode){
    	      			var datar = result.data;
    	      			var webStatus = datar.webStatus;
    	      			var str = "";
    	      			if(webStatus === "1"){
    	      				str = "<span class='layui-badge layui-bg-green'>正常</span>";
    	      			}else if(webStatus == "3"){
    	      				str = "<span class='layui-badge layui-bg-orange'>应用报错</span>";
    	      			}else if(webStatus == "5"){
    	      				str = "<span class='layui-badge'>打包失败</span>";
    	      			}else if(webStatus == "8"){
    	      				str = "<span class='layui-badge'>分支下载失败</span>";
    	      			}else if(webStatus == "9"){
    	      				str = "<span class='layui-badge'>分支更新失败</span>";
    	      			}
    	      			if(str != ""){
    	      				$('table tr[data-index="'+i+'"] td[data-field="webStatus"] div').html(str);
    	      				clearInterval(timer);
    	      				flag = false;
    	      			}
    	      		}
              }
            });
      }
    }, 1000);
  }
  var queryEsbUrlAndDataSource = function(i,data){
	  admin.req({
          url: '/deploy/queryEsbUrlAndDataSource.do'
          ,data: JSON.stringify({id:data[i].id})
          ,success: function(result){
	      		if(result.code == successCode){
	      			var datar = result.data;
	      			var esbstr = datar.env.envName+" ";
	      			esbstr += datar.env.appIp+":"+datar.env.appPort;
	      			var sourcestr = datar.db.ip+":"+datar.db.port+" ";
	      			if(datar.db.isSource === "0"){
	      				sourcestr += "<span class='layui-badge'>直连</span>";
	      			}
	      			$('table tr[data-index="'+i+'"] td[data-field="esb"] div').html(esbstr);
	      			$('table tr[data-index="'+i+'"] td[data-field="dataSource"] div').html(sourcestr);
	      		}
          }
        });
  }
  var checkAppServer = function(i, data){
	  admin.req({
          url: '/deploy/checkAppServer.do'
          ,data: JSON.stringify({id:data[i].id})
          ,success: function(result){
	      		if(result.code == successCode){
	      			var datar = result.data;
	      			var appStatus = datar.appStatus;
	      			var str = "<span class='layui-badge layui-bg-green'>正常</span>";
	      			if(appStatus === "0"){
	      				str = "<span class='layui-badge'>无进程</span>";
	      			}else if(appStatus == "2"){
	      				str = "<span class='layui-badge layui-bg-blue'>准备就绪</span>";
	      			}else if(appStatus == "3"){
	      				str = "<span class='layui-badge layui-bg-orange'>应用报错</span>";
	      			}else if(appStatus == "4"){
	      				str = "<span class='layui-badge layui-bg-orange'>打包中</span>";
	      			}else if(appStatus == "5"){
	      				str = "<span class='layui-badge'>打包失败</span>";
	      			}else if(appStatus == "6"){
	      				str = "<span class='layui-badge layui-bg-green'>空闲</span>";
	      			}else if(appStatus == "7"){
	      				str = "<span class='layui-badge layui-bg-green'>分支下载中</span>";
	      			}else if(appStatus == "8"){
	      				str = "<span class='layui-badge'>分支下载失败</span>";
	      			}else if(appStatus == "9"){
	      				str = "<span class='layui-badge'>分支更新失败</span>";
	      			}
	      			$('table tr[data-index="'+i+'"] td[data-field="appStatus"] div').html(str);
	      		}
          }
        });
  }
  var checkWebServer = function(i ,data){
	  if(data[i].editionInfo.type == "2"){//后管 无web状态
			var str = "<span class='layui-badge layui-bg-green'>无web状态</span>";
			$('table tr[data-index="'+i+'"] td[data-field="webStatus"] div').html(str);
			return;
		}
		admin.req({
          url: '/deploy/checkWebServer.do'
          ,data: JSON.stringify({id:data[i].id})
          ,success: function(result){
	      		if(result.code == successCode){
	      			var datar = result.data;
	      			var webStatus = datar.webStatus;
	      			var str = "<span class='layui-badge layui-bg-green'>正常</span>";
	      			if(webStatus === "0"){
	      				str = "<span class='layui-badge'>无进程</span>";
	      			}else if(webStatus == "2"){
	      				str = "<span class='layui-badge layui-bg-blue'>准备就绪</span>";
	      			}else if(webStatus == "3"){
	      				str = "<span class='layui-badge layui-bg-orange'>应用报错</span>";
	      			}else if(webStatus == "4"){
	      				str = "<span class='layui-badge layui-bg-orange'>打包中</span>";
	      			}else if(webStatus == "5"){
	      				str = "<span class='layui-badge'>打包失败</span>";
	      			}else if(webStatus == "6"){
	      				str = "<span class='layui-badge layui-bg-green'>空闲</span>";
	      			}else if(webStatus == "7"){
	      				str = "<span class='layui-badge layui-bg-green'>分支下载中</span>";
	      			}else if(webStatus == "8"){
	      				str = "<span class='layui-badge'>分支下载失败</span>";
	      			}else if(webStatus == "9"){
	      				str = "<span class='layui-badge'>分支更新失败</span>";
	      			}
	      			$('table tr[data-index="'+i+'"] td[data-field="webStatus"] div').html(str);
	      		}
          }
        });
  }
  //用户管理
  table.render({
    elem: '#LAY-deploy-manage'
    ,url: '/deploy/queryPage.do'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'envMark', minwidth: 200, title: '手机环境' ,templet: '#tmplphone'}
      ,{field: 'esb', minwidth: 200, title: '整合环境' ,templet: '#tmplesb2'}
      ,{field: 'esbName', minwidth: 150, title: '分支类型' ,templet: '#tmplbranch'}
      //,{field: 'dataSource', title: '数据源', width: 150,templet: '#tmplesb'}
      //,{field: 'appStatus', title: 'app状态', width: 150,templet: '#tmplesb'}
      //,{field: 'webStatus', title: 'web状态', width: 150,templet: '#tmplesb'}
      ,{title: '操作', align:'center', fixed: 'right', toolbar: '#table-useradmin-webuser'}
    ]]
    ,page: true
    ,limit: 30
    ,height: 'full-320'
    ,text: '对不起，加载出现异常！'
    ,done:function(res, curr, count){
    	return;
    	var data = res.data;
    	if(data != null && data != ''){
    		var size = data.length;
    		var flag = true;
    		var flagw = true;
    		for(var i =0; i<size; i++){
    			queryEsbUrlAndDataSource(i, data);
    			if(data[i].editionInfo.type == "2"){ //后管
					checkWebServer(i, data);
    			}
    			if(data[i].id == deployId){
    				deployId = -1;
    				progress(i, data);
    				flag = false;
    			}
    			if(data[i].id == deployWebId){
    				deployWebId = -1;
    				progressWeb(i, data);
    				flagw = false;
    			}
    			if(!flag && flagw){
    				checkWebServer(i, data);
    			}
    			if(!flagw && flag){
    				checkAppServer(i, data);
    			}
    			if(flag && flagw){
    				checkWebServer(i, data);
    				checkAppServer(i, data);
    			}
    			flag = true;
    			flagw = true;
    		}
    	}
    }
  });
  
  //监听工具条
  table.on('tool(LAY-svn-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
    	layer.confirm('确认删除吗?', function(index){
    		admin.req({
                url: '/deploy/delete.do'
                ,data: JSON.stringify(data)
                ,success: function(res){
    	      		if(res.code == successCode){
    	      			obj.del();
    	      			layer.close(index);
    	      		}else{
    	      			layer.close(index);
    	      		}
                }
              });
        	 });
    } else if(obj.event === 'edit'){
      data.updateFlag = true;
      admin.popup({
        title: '编辑信息'
        ,area: ['500px', '500px']
        ,id: 'LAY-popup-driver-add'
        ,success: function(layero, index){
          view(this.id).render('env/deploy/deployform', data).done(function(){
            form.render(null, 'layuiadmin-form-svnadmin');
            //监听提交
            form.on('submit(LAY-svn-front-submit)', function(data){
              var field = data.field; //获取提交的字段

              //提交 Ajax 成功后，关闭当前弹层并重载表格
              admin.req({
                  url: '/deploy/update.do'
                  ,data: JSON.stringify(field)
                  ,success: function(res){
            		if(res.code == successCode){
            			layui.table.reload('LAY-deploy-manage'); //重载表格
                        layer.close(index); //执行关闭 
            		}else{
            			layer.close(index);
            		}
                  }
                });
            });
          });
        }
      });
    }else if(obj.event === 'deploy'){
    	admin.popup({
            title: '部署'
            ,area: ['500px', '450px']
            ,id: 'LAY-popup-deploy-env'
            ,success: function(layero, index){
              view(this.id).render('env/deploy/deploy', data).done(function(){
                form.render(null, 'layuiadmin-form-svnadmin');
                //监听提交
                form.on('submit(LAY-svn-front-submit)', function(result){
                  var field = result.field; //获取提交的字段
                  
                  //提交 Ajax 成功后，关闭当前弹层并重载表格
                  admin.req({
                      url: '/deploy/operationService.do'
                      ,data: JSON.stringify(field)
                      ,success: function(res){
                		if(res.code == successCode){
                			if(field.warType != null){
                      		  deployId = data.id;
                            }
                            if(field.tarType != null){
                          	  deployWebId = data.id;
                            }
                            
                			layui.table.reload('LAY-deploy-manage'); //重载表格
                            layer.close(index); //执行关闭 
                		}else{
                			layer.close(index);
                		}
                      }
                    });
                });
              });
            }
          });
    }
  });
  
  exports('deploy', {})
});