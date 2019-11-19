/**

 @Name：分支管理
 @Author：jiangjun
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */


layui.define(['table', 'form','laydate'], function(exports){
  var $ = layui.$
  ,admin = layui.admin
  ,view = layui.view
  ,table = layui.table
  ,form = layui.form
  ,setter = layui.setter;
  var laydate = layui.laydate;
  var successCode = setter.response.statusCode.ok;
  //用户管理
  table.render({
    elem: '#LAY-svn-manage'
    ,url: '/branch/queryPage.do'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      //,{field: 'proFlag', title: '生产分支', minWidth: 100, templet: '#tmpltype'}
      ,{field: 'branchName', title: '分支名称', width: 400,templet: '#tmpltype'}
      ,{field: 'beonlineDate', title: '上线日期', minWidth: 100, templet:'#templdate'}
      ,{field: 'proBranchId', title: '父分支', minWidth: 100, templet:'#templbranch'}
      //,{field: 'branchVersion', title: '工件号', width: 100}
      ,{field: 'status', title: '合并状态', minWidth: 100,templet:'#templstatus'}
      ,{field: 'svnAddress', minWidth: 100, title: 'svn地址' }
      ,{field: 'type', minWidth: 100, title: '上线资料' ,templet:'#templdata'}
      //,{field: 'saveDate', title: '时间', sort: false, templet: '#tmplsaveDate'}
      ,{title: '操作', width: 150, align:'center', fixed: 'right', toolbar: '#table-useradmin-webuser'}
    ]]
    ,page: true
    ,limit: 30
    ,height: 'full-320'
    ,text: '对不起，加载出现异常！'
  });
  
  //监听工具条
  table.on('tool(LAY-svn-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
    	layer.confirm('确认删除吗?', function(index){
    		admin.req({
                url: '/branch/delete.do'
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
        title: '编辑分支'
        ,area: ['500px', '600px']
        ,id: 'LAY-popup-user-add'
        ,success: function(layero, index){
          view(this.id).render('branch/branchform', data).done(function(){
            form.render(null, 'layuiadmin-form-svnadmin');
            //监听提交
            form.on('submit(LAY-svn-front-submit)', function(data){
              var field = data.field; //获取提交的字段
              var editionInfoi = field.editionInfo;
              var editionInfo = field.editionInfo+"|";
              for(var item in field){
            	  if(item.indexOf("fushuInfo") > -1){
            		  editionInfo += field[item] + "|"
            		  delete field[item]
            	  }
              }
              editionInfo = editionInfo.substr(0,editionInfo.length-1);
              field.editionInfo = editionInfo;
              
              var beonlineDate = field.beonlineDate;
              var beonlineDates = beonlineDate.split("|");
              field.beonlineDate = beonlineDates[0];
              field.beonlineId = beonlineDates[1];
              
              var beonlieData = "";
              for(var item in field){
            	  if(item.indexOf("beonlineData") > -1){
            		  beonlieData += field[item] + "|"
            		  delete field[item]
            	  }
              }
              if(field.houguandata == null){
            	  beonlieData = beonlieData.substr(0,beonlieData.length-1);
              }else{
            	  beonlieData += field.houguandata;
            	  delete field.houguandata;
              }
              field.beonlineData = beonlieData;
              
              var proBranchId = "";
              if("0" === editionInfoi){
            	  proBranchId = field.proBranchId;
              }else if("1" === editionInfoi){
            	  proBranchId = field.proBranchId2;
              }else if("3" === editionInfoi){
            	  proBranchId = field.proBranchId3;
              }
              delete field.proBranchId;
              delete field.proBranchId2;
              delete field.proBranchId3;
			  field.proBranchId = proBranchId;  
              
              //提交 Ajax 成功后，关闭当前弹层并重载表格
              admin.req({
                  url: '/branch/update.do'
                  ,data: JSON.stringify(field)
                  ,success: function(res){
            		if(res.code == successCode){
            			layui.table.reload('LAY-svn-manage'); //重载表格
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

  exports('branch', {})
});