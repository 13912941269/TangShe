function uploadBtnChange(imgFile,resinput,resimg,basepath){
    if(window.File && window.FileReader && window.FileList && window.Blob){ 
    	//获取上传file
    	if($("#"+imgFile).val()!=""){
            var filedata = document.getElementById(imgFile).files[0];
            var size= filedata.size;
            if(size==0){
                alert("图片不存在！");
                return false;
            }
            processfile(imgFile,resinput,resimg,basepath);
    	}
    }else{
        alert("此浏览器不完全支持压缩上传图片");
    }
}


function processfile(file,resinput,resimg,basepath) {
	var respath="";
    var filedata = document.getElementById(file).files[0];
	var filename=filedata.name;
    var reader = new FileReader();
    reader.readAsArrayBuffer(filedata);
    reader.onload = function (event) {
        var blob = new Blob([event.target.result]); 
        window.URL = window.URL || window.webkitURL;
        var blobURL = window.URL.createObjectURL(blob); 
        var image = new Image();
        image.src = blobURL;
        image.onload = function() {
            var resized = resizeMe(image);
            $.ajax({
                async: false,
                url: basepath+'/uploadfile',
                type: 'POST',    
                data: {base64bob:resized,filename:filename},     
                success: function (returndata) {
                    if(returndata.code=="200"){
                        respath=returndata.data;
                        $("#"+resinput).val(respath);
                        $("#"+resimg).attr("src",basepath+"/projectimg/"+respath);
                    }
                }    
           }); 
        }
    }
    return respath;
}

function resizeMe(img) {
    //压缩的大小
    /*var max_width = 800;
    var max_height = 1200; */
    var canvas = document.createElement('canvas');
    var width = img.width;
    var height = img.height;
    /*if(width > height) {
        if(width > max_width) {
            height = Math.round(height *= max_width / width);
            width = max_width;
        }
    }else{
        if(height > max_height) {
            width = Math.round(width *= max_height / height);
            height = max_height;
        }
    }*/
    canvas.width = width;
    canvas.height = height;
    var ctx = canvas.getContext("2d");
    ctx.fillStyle="#FFFFFF";
    ctx.fillRect(0,0,width,height);
    ctx.drawImage(img, 0, 0,width,height);
    //压缩率
    return canvas.toDataURL("image/jpeg",0.8);
}