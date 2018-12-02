/**
 * 系统日志脚本
 * waltercat
 * 20181202
 */

var curSysLog = {
		
		//日志查询
		queryLog : function(){
			$curTable.ajax.reload();
		},
		
		getCheckedSysLog : function(){
			var logIDS = "";
			$("[name=chkItem]:checkbox").each(function(){
				if( $(this).prop("checked")){
					logIDS+=$(this).val()+",";
				}
				
			});			
			return logIDS;
		},
		
		checkAll : function( obj ){
			$("[name=chkItem]:checkbox").prop("checked",obj.checked);
		},
		
		//批量删除
		deleteLogs : function( obj, logIDS ){
				layer.confirm('确认要删除吗？',function(index){
					$.ajax({
						url : curSysLog.deleteSyslogsURL,
						async : false,
						type:'DELETE',
						contentType:'application/json',
						data : logIDS,
						dataType:'json',
						success : function( logJSON ){
							
							curSysLog.delSysLogView();
							
							layer.msg('已删除!',{icon:1,time:1000});
						},
						error : function( errorJSON ){}
				});
			});
		},
		
		delSysLogView : function(){
			$("[name=chkItem]:checkbox").each(function(){
				if( $(this).prop("checked")){
					
					$(this).parents("tr").remove();
				}
			});
		},
		
		
		deleteLog : function( obj, logID ){
			layer.confirm('确认要删除吗？',function(index){
				$.ajax({
					url : curSysLog.deleteSyslogURL+"/"+logID,
					async : false,
					type:'DELETE',
					contentType:'application/json',
					dataType:'json',
					success : function( permissionJSON ){
						$(obj).parents("tr").remove();
						layer.msg('已删除!',{icon:1,time:1000});
					},
					error : function( errorJSON ){}
			});
		});
		},
		
		getLogsForPage : "/system/log/getSysLogByPage",
		
		deleteSyslogURL : "/system/log/delTLog",
		
		deleteSyslogsURL : "/system/log/delTLogs"
		
}
var $curTable = $('#table_list_id').dataTable({
	"striped": true,
	"processing": true,
    "serverSide": true,
    "bStateSave": true,
    "aaSorting": [[ 1, "desc" ]],//默认第几个排序
    "sPaginationType": "full_numbers",
    ajax : {
    	"url" : curSysLog.getLogsForPage,
    	"type" : 'get',
    	"data" : function( d ){
    		d.f_log_time_begin = $('#f_log_time_begin').val();
    		d.f_log_time_end = $('#f_log_time_end').val();
    	}
    },
    "aLengthMenu": [[5, 10, 20, 100], [5, 10, 20, 100]],  
    "iDisplayLength":5 ,
    "columns" :[
    	{data:'id', "bSortable": false,render:function( data, type, full, meta ){ return '<input type="checkbox" name="chkItem" permissionCode="'+full.permissionCode+'" class="productCheckBox" name="productCheckBox" value="' + data + '" />'; }},
    	{data:'id',render:function( data, type, full, meta ){return data}},
    	{data:'f_org_name',render:function( data, type, full, meta ){return data}},
    	{data:'f_user_name',render:function( data, type, full, meta ){return data}},
    	{data:'f_log_time_value',render:function( data, type, full, meta ){return data}},
    	{data:'f_log_name',render:function( data, type, full, meta ){return data}},
    	{data:'id',render:function( data, type, full, meta ){
    		return "<a title=\'删除\' href=\'javascript:;\' onclick=\'curSysLog.deleteLog(this,\""+data+"\")\' class=\'ml-5\' style=\'text-decoration:none\'><i class=\'Hui-iconfont\'>&#xe6e2;</i></a>"}}
    ],
    "aoColumnDefs": [{ "bSearchable": false, "bVisible": false, "aTargets": [1] }]//隐藏ID列。 
}).api();//

$(function(){
	
	//日期插件
	$("#f_log_time_begin").datetimepicker({
		format: 'yyyy-mm-dd',
		minView: "month",
		todayBtn:  1,
		autoclose: 1,
		endDate : new Date()
	}).on('hide',function(e) {
		//此处可以触发日期校验。
	});
	
	//日期插件
	$("#f_log_time_end").datetimepicker({
		format: 'yyyy-mm-dd',
		minView: "month",
		todayBtn:  1,
		autoclose: 1,
		endDate : new Date()
	}).on('hide',function(e) {
		//此处可以触发日期校验。
	});
})
