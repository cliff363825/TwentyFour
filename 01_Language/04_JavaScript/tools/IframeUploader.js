'use strict';

/**
 * iframe异步上传文件
 * @param fileId 文件上传控件id
 * @param iframeId iframe id
 * @param uploadUrl 文件上传处理url
 * @constructor
 */
function IframeUploader(fileId, iframeId, uploadUrl) {
    this.fileId = fileId;
    this.iframeId = iframeId;
    this.uploadUrl = uploadUrl;
}
/**
 * 创建iframe
 * @param iframeId iframe id
 * @returns {Element}
 * @private
 */
IframeUploader.prototype._createIframe = function (iframeId) {
    var iframeObj = document.getElementById(iframeId);
    if (!iframeObj) {
        iframeObj = document.createElement('iframe');
        iframeObj.setAttribute('id', iframeId);
        iframeObj.setAttribute('name', iframeId);
        iframeObj.setAttribute('width', '0');
        iframeObj.setAttribute('height', '0');
        iframeObj.setAttribute('style', 'display:none;');
        document.body.appendChild(iframeObj);
        iframeObj.onload = function () {
            var content;
            if (iframeObj.contentDocument) {
                content = iframeObj.contentDocument.body.innerHTML;
            } else if (iframeObj.contentWindow) {
                content = iframeObj.contentWindow.document.body.innerHTML;
            } else if (iframeObj.document) {
                content = iframeObj.document.body.innerHTML;
            }
            try {
                content && eval(content);
            } catch (e) {
                console.log(e);
            }
            setTimeout(function () {
                iframeObj.parentNode.removeChild(iframeObj);
            }, 100);
        };
    }
    return iframeObj;
};
/**
 * 创建表单
 * @param fileId 文件上传控件id
 * @param uploadUrl 文件上传处理url
 * @returns {Element}
 * @private
 */
IframeUploader.prototype._createForm = function (fileId, uploadUrl) {
    var formObj = document.createElement('form');
    formObj.setAttribute('action', uploadUrl);
    formObj.setAttribute('method', 'post');
    formObj.setAttribute('enctype', 'multipart/form-data');
    var fileObj = document.getElementById(fileId);
    // var fileObjClone = fileObj.cloneNode(true);
    // fileObj.parentNode.insertBefore(fileObjClone, fileObj);
    formObj.appendChild(fileObj.cloneNode(true));
    var csrfObj = document.createElement('input');
    csrfObj.setAttribute('type', 'hidden');
    var metaArray = document.getElementsByTagName('meta');
    for (var i = 0; i < metaArray.length; i++) {
        if (metaArray[i].getAttribute('name') == 'csrf-param') {
            csrfObj.setAttribute('name', metaArray[i].getAttribute('content'));
        }
        if (metaArray[i].getAttribute('name') == 'csrf-token') {
            csrfObj.setAttribute('value', metaArray[i].getAttribute('content'));
        }
    }
    formObj.appendChild(csrfObj);
    return formObj;
};
/**
 * 文件上传入口
 */
IframeUploader.prototype.upload = function () {
    var iframeObj = this._createIframe(this.iframeId);
    var formObj = this._createForm(this.fileId, this.uploadUrl);
    if (iframeObj.contentDocument) {
        iframeObj.contentDocument.body.appendChild(formObj);
    } else if (iframeObj.contentWindow) {
        iframeObj.contentWindow.document.body.appendChild(formObj);
    } else if (iframeObj.document) {
        iframeObj.document.body.appendChild(formObj);
    }
    formObj.submit();
};

function demo(obj) {
    console.log(obj);
}
