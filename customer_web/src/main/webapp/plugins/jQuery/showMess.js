function showMess(url,id,redirectUrl) {   	
        $.ajax({
            method:"post",
            url:url,
            data:{'id':id},
            //async:false,
            success:function(exeDetail){           	
            	if(exeDetail != null && exeDetail != ""){
            		layer.alert(exeDetail, {
            		    skin: 'layui-layer-lan'
            		    ,closeBtn: 0
            		    //,time: 5000 //2秒后自动关闭
            		    //,anim: 1 //动画类型
            		  });
            	/*layer.open({
                    type: 1,
                    title:['<span style="color:white;">消息</span> ','background-color: #4898d5' ],
                    //time: 2000, //2秒后自动关闭
                    area : ['300px ,80px'],
                    shade: 0.5,
                    id: 'LAY_layuipro',
                    moveType: 1,
                    btn: ['确定'],
                 content: '<div id="exe_detail" class="gray-bg  pace-done" style="padding: 50px;  line-height: 22px; font-weight: bold;" ><p style="word-wrap:break-word; word-break:break-all;">'+exeDetail+'</p></div>',
                    closeBtn:1
                  });*/
            	}else{
            		location=redirectUrl; 
            	}
            },
        });
    }