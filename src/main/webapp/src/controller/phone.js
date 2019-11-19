/**

 @Name：手机版本管理
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
    ,url: '/editionInfo/queryPage.do'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'type', title: '版本类型' ,templet: '#tmpltype'}
      ,{field: 'appName',  title: '应用名称'}
      ,{field: 'webName', title: '静态名称'}
      ,{field: 'dataSource', title: '数据源'}
      ,{field: 'logAddress', title: '日志地址'}
      ,{field: 'logName', title: '日志名称',width: 150}
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
                url: '/beonlineEdition/delete.do'
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
        title: '编辑用户'
        ,area: ['500px', '700px']
        ,id: 'LAY-popup-user-add'
        ,success: function(layero, index){
          view(this.id).render('edition/phone/phoneform', data).done(function(){
            form.render(null, 'layuiadmin-form-svnadmin');
            laydate.render({
                elem: '#test-laydate-normal-cn',
                calendar: true,
                value:data.beonlineDate
              });
            //监听提交
            form.on('submit(LAY-svn-front-submit)', function(data){
              var field = data.field; //获取提交的字段

              //提交 Ajax 成功后，关闭当前弹层并重载表格
              admin.req({
                  url: '/editionInfo/update.do'
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

  exports('phone', {})
});