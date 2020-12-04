$(document).ready(function() {

    tableAplicaciones = $('#appList').dataTable({
        "language" : {
            "sProcessing" : "Procesando...",
            "sLengthMenu" : "Mostrar _MENU_",
            "sZeroRecords" : "No se encontraron resultados",
            "sEmptyTable" : "Ningún dato disponible en esta tabla",
            "sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix" : "",
            "sSearch" : "Buscar",
            "sUrl" : "",
            "sInfoThousands" : ",",
            "sLoadingRecords" : "Cargando...",
            "oPaginate" : {
                "sFirst" : "Primero",
                "sLast" : "Último",
                "sNext" : "Siguiente",
                "sPrevious" : "Anterior"
            },
            "oAria" : {
                "sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending" : ": Activar para ordenar la columna de manera descendente"
            }
        },
        "lengthMenu" : [ [ 10, 25, 50, -1 ], [ 10, 25, 50, "Todo" ] ],
        "pageLength" : 25,
        "dom" : '<"top fld dsp_ib" f><"fld dsp_ib" l>t<"bottom" ir><"clear">',
        "bSort" : true,
        "aoColumns" : [ {
            "bSortable" : false,
            "width" : "20%"
        }, {
            "bSortable" : false,
            "width" : "20%"
        }, {
            "bSortable" : false,
            "width" : "20%"
        }, {
            "bSortable" : false,
            "width" : "20%"
        }, {
            "bSortable" : false,
            "width" : "20%"
        }, {
            "bSortable" : false,
            "width" : "10%"
        }, {
            "bSortable" : false,
            "width" : "10%"
        }, {
            "class" : "tc",
            "bSortable" : false,
            "width" : "5%"
        } ],
        "paging" : true
    });

});

function createButtonsDialog(buttons) {
    var ret = $('<ul class="mf-buttonbar">');
    $(buttons).each(
            function(k, v) {
                $(
                        '<li class="mf-buttonbar--item"><button ' + (v.submit ? 'type="submit"' : '')
                                + ' class="buttonbar--btn ' + v.clase + '" href="#!">' + v.texto + '</a></li>')
                        .appendTo(ret);
            });
    return ret;
}

function showDialogActivate(app, mensaje) {
    var divData = $('#init-modal-desactivar-app');
    divData.find('#labelBorrar').text(mensaje);
    var data = divData.data();

    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-borrar',
        texto : 'Confirmar'
    } ]);

    buttons.find('.js-documento-borrar').on('click', function(e) {
        desactivar(app);
        $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();
    $mf.my_dialog.appendDialog(data, true, true);
}

function showDialogErase(app) {
    var divData = $('#init-modal-eliminar-app');
    var data = divData.data();

    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-borrar',
        texto : 'Confirmar'
    } ]);

    buttons.find('.js-documento-borrar').on('click', function(e) {
        eliminarAplicacion(app);
        $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();
    $mf.my_dialog.appendDialog(data, true, true);
}

function showDialogAlta() {
    var divData = $('#init-modal-alta-app');
    var data = divData.data();

    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-alta',
        texto : 'Aceptar'
    } ]);

    buttons.find('.js-documento-alta').on('click', function(e) {
        altaAplicacion();
        $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();
    $mf.my_dialog.appendDialog(data, true, true);
}

function showDialogEdit(identificador, email, responsable, telefono, aditional, codigounidad, serialNumberCertificado,
        numeroProcedimiento) {
    var divData = $('#init-modal-alta-app');
    var data = divData.data();

    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-alta',
        texto : 'Aceptar'
    } ]);

    buttons.find('.js-documento-alta').on('click', function(e) {
        altaAplicacion();
        $mf.my_dialog.close_dialog();
    });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();
    $mf.my_dialog.appendDialog(data, true, true);
    $('.mf-dialog [name=identificador]').val(identificador);
    if (email != "null") {
        $('.mf-dialog [name=email]').val(email);
    }
    if (responsable != "null") {
        $('.mf-dialog [name=responsable]').val(responsable);
    }
    if (telefono != "null") {
        $('.mf-dialog [name=telefono]').val(telefono);
    }
    if (codigounidad != "null") {
        $('.mf-dialog [name=codigoUnidadOrganica]').val(codigounidad);
    }

    if (serialNumberCertificado != "null") {
        $('.mf-dialog [name=serialNumberCertificado]').val(serialNumberCertificado);
    }

    if (numeroProcedimiento != "null") {
        $('.mf-dialog [name=numeroProcedimiento]').val(numeroProcedimiento);
    }

    $(aditional.split(";;")).each(function(index, element) {
        var data = element.split("=");
        if (document.getElementsByName("aditional_" + data[0])[1] != null) {
            document.getElementsByName("aditional_" + data[0])[1].value = data[1];
        }
    });
}

function desactivar(identificador) {
    if (identificador != "") {
        $("#desactivarAplicacionForm #identificador").val(identificador);
        $("#desactivarAplicacionForm").submit();
    }
}

function eliminarAplicacion(identificador) {
    if (identificador != "") {
        $("#eliminarAplicacionForm #identificador").val(identificador);
        $("#eliminarAplicacionForm").submit();
    }
}

function altaAplicacion() {
    $("#altaAplicacionForm #identificador").val($('.mf-dialog [name=identificador]').val());
    $("#altaAplicacionForm #password").val($('.mf-dialog [name=password]').val());
    $("#altaAplicacionForm #responsable").val($('.mf-dialog [name=responsable]').val());
    $("#altaAplicacionForm #email").val($('.mf-dialog [name=email]').val());
    $("#altaAplicacionForm #telefono").val($('.mf-dialog [name=telefono]').val());
    $("#altaAplicacionForm #codigoUnidadOrganica").val($('.mf-dialog [name=codigoUnidadOrganica]').val());
    $("#altaAplicacionForm #serialNumberCertificado").val($('.mf-dialog [name=serialNumberCertificado]').val());
    $("#altaAplicacionForm #numeroProcedimiento").val($('.mf-dialog [name=numeroProcedimiento]').val());

    var aditional = "";
    $('.mf-dialog [name*=aditional_]').each(function(index, element) {
        if (aditional != "") {
            aditional += ";;";
        }
        aditional += element.name.substr(10, element.name.length);
        aditional += "=";
        aditional += $(element).val();
    });

    $("#altaAplicacionForm #aditional").val(aditional);
    $("#altaAplicacionForm").submit();
}
