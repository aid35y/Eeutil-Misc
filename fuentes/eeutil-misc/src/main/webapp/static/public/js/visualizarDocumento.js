function visualizarDocumento(errorContenido, errorExtension) {
    var filename = $("#documento").val();
    var extension = filename.replace(/^.*\./, '');
    if (extension == filename) {
        extension = '';
    } else {
        extension = extension.toLowerCase();
    }

    if (filename == '') {
        alert(errorContenido);
    } else if (extension != 'xml') {
        alert(errorExtension);
    } else {
        var file = $("#documento")[0].files[0];
        var fr = new FileReader();
        fr.onload = receivedBinary;
        fr.readAsDataURL(file);
    }

    function receivedBinary() {
        var dataB64 = formatResult(fr.result);
        $("#visualizar").val(dataB64);
        $("#visualizarDocumentoForm").submit();
    }

    function formatResult(tr) {
        var pattern = ';base64,';
        return tr.substring(tr.indexOf(pattern) + pattern.length, tr.length);
    }
}