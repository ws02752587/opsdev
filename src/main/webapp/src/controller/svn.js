/**

 @Name：SVN管理
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
  var successCode = setter.response.statusCode.ok;
  //用户管理
  table.render({
    elem: '#LAY-svn-manage'
    ,url: '/svn/queryPage.do'
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'svnName', title: 'svn名称', width: 120}
      ,{field: 'svnBaseAddress', title: 'svn地址', minWidth: 150}
      ,{field: 'svnType', width: 100, title: 'svn类型' ,templet: '#tmpltype'}
      ,{field: 'saveDate', title: '时间', sort: false, templet: '#tmplsaveDate'}
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
    	
    } else if(obj.event === 'edit'){
    	data.updateFlag = true;
      admin.popup({
        title: '编辑用户'
        ,area: ['500px', '450px']
        ,id: 'LAY-popup-user-add'
        ,success: function(layero, index){
          view(this.id).render('svn/svnform', data).done(function(){
            form.render(null, 'layuiadmin-form-svnadmin');
            
            //监听提交
            form.on('submit(LAY-svn-front-submit)', function(data){
              var field = data.field; //获取提交的字段

              //提交 Ajax 成功后，关闭当前弹层并重载表格
              admin.req({
                  url: '/svn/update.do'
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

  exports('svn', {})
});