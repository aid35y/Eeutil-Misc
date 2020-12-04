$(document)
        .ready(
                function() {

                    $mf.timer.on($('#contentData'));

                    tableUsuarios = $('#userList')
                            .dataTable(
                                    {
                                        "language" : {
                                            "sProcessing" : "Procesando...",
                                            "sLengthMenu" : "Mostrar _MENU_",
                                            "sZeroRecords" : "No se encontraron resultados",
                                            "sEmptyTable" : "Ningún dato disponible en esta tabla",
                                            "sInfo" : "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                                            "sInfoEmpty" : "Mostrando registros del 0 al 0 de un total de 0 registros",
                                            "sInfoFiltered" : "",
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
                                        "lengthMenu" : [ [ 10, 25, 50, 99999 ], [ 10, 25, 50, "Todo" ] ],
                                        "pageLength" : 25,
                                        "processing" : true,
                                        "serverSide" : true,
                                        "ajax" : "usuariosListaJson?app=Inside",
                                        "dom" : '<"top fld dsp_ib" f><"fld dsp_ib" l>t<"bottom" ir><"clear">',
                                        "bSort" : true,
                                        "aoColumns" : [
                                                {
                                                    "bSortable" : false,
                                                    "width" : "10%",
                                                    "mData" : "identificador"
                                                },
                                                {
                                                    "bSortable" : false,
                                                    "width" : "10%",
                                                    "mData" : "codigoUnidadOrganica"
                                                },
                                                {
                                                    "bSortable" : false,
                                                    "width" : "40%",
                                                    "mData" : "unidadDescripcion"
                                                },
                                                {
                                                    "bSortable" : false,
                                                    "width" : "20%",
                                                    "mData" : "numeroProcedimiento"
                                                },
                                                {
                                                    "bSortable" : false,
                                                    "width" : "10%",
                                                    "mData" : "activo"
                                                },
                                                {
                                                    "bSortable" : false,
                                                    "width" : "5%",
                                                    "data" : null,
                                                    "render" : function(obj) {
                                                        return '<a id="btn_editar_documento" title="Modificar" href="#!" class="mf-table-data--row-action  mf-action  mf-action__editar"'
                                                                + 'onclick="showDialogEdit(&#39;'
                                                                + obj.identificador
                                                                + '&#39;)">'
                                                                + '<span class="mf-icon mf-icon-doc-config"></span></a>';
                                                    }
                                                } ],
                                        "paging" : true
                                    });

                    $mf.timer.off($('#contentData'));

                    tableDir3 = $('#dir3List')
                            .DataTable(
                                    {
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
                                        "lengthMenu" : [ [ 10, 25, 50, 999999 ], [ 10, 25, 50, "Todo" ] ],
                                        "pageLength" : 25,
                                        "dom" : '<"top fld dsp_ib" f><"fld dsp_ib" l>t<"bottom" ir><"clear">',
                                        "bSort" : false,
                                        "bFilter" : false,
                                        "columns" : [
                                                {
                                                    "data" : "codigo"
                                                },
                                                {
                                                    "data" : "numeroProcedimiento"
                                                },
                                                {
                                                    "data" : "activo",
                                                    "render" : function(data, type, row) {
                                                        if (row.activo == true) {
                                                            return '<input type="radio" name="activoradio" id="id_check_'
                                                                    + row.codigo + '" checked>';
                                                        } else {
                                                            return '<input type="radio" name="activoradio" id="id_check_'
                                                                    + row.codigo + '" >';
                                                        }
                                                    },
                                                    className : "dt-body-center"
                                                }

                                        ],
                                        "paging" : true,
                                        "fnRowCallback" : function(nRow, aData) {
                                            var activo = aData.activo;
                                            var $nRow = $(nRow);
                                            if (activo) {
                                                $nRow.css({
                                                    "background-color" : "#779944"
                                                }, {
                                                    "font-weight" : "bold"
                                                })
                                            } else if ("activo") {
                                                $nRow.css({
                                                    "background-color" : "#c94a53"
                                                }, {
                                                    "font-weight" : "bold"
                                                })
                                            }
                                            return nRow
                                        }

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

function showDialogDelete(user) {
    var divData = $('#init-modal-borrar-usuario');
    var data = divData.data();

    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-borrar',
        texto : 'Confirmar'
    } ]);

    buttons.find('.js-documento-borrar').on('click', function(e) {
        borrarUsuario(user);
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
    var divData = $('#init-modal-alta-usuario');
    var data = divData.data();

    var buttons = createButtonsDialog([ {
        clase : 'js-documento-cancelar',
        texto : 'Cancelar'
    }, {
        clase : 'js-documento-alta',
        texto : 'Aceptar'
    } ]);

    buttons.find('.js-documento-alta').on(
            'click',
            function(e) {
                altaUsuario($('.mf-dialog [name=nif]').val(), $('.mf-dialog [name=codigoUnidadOrganica]').val(), $(
                        '.mf-dialog [name=numeroProcedimiento]').val());
                $mf.my_dialog.close_dialog();
            });

    buttons.find('.js-documento-cancelar').on('click', function(e) {
        $mf.my_dialog.close_dialog();
    });

    data.buttons = buttons;
    data.data = divData.html();
    $mf.my_dialog.appendDialog(data, true, true);
}

function borrarUsuario(user) {
    if (user != "") {
        $("#borrarUsuarioForm #identificador").val(user);
        $("#borrarUsuarioForm").submit();
    }
}

function altaUsuario(user, codUnidadOrganica, numeroProcedimiento) {
    if (user != "") {
        //0 es ROLE_USER de inside. 1 es ALTA_USUARIOS_ROLE en insideRol esquema de inside
        var altaUsuarioRole = 0;
        if ($(".mf-dialog #altaUsuarioRole").prop("checked")) {
            altaUsuarioRole = 1;
        }

        $("#altaUsuarioForm #identificador").val(user);
        $("#altaUsuarioForm #codigoUnidadOrganica").val(codUnidadOrganica);
        $("#altaUsuarioForm #numeroProcedimiento").val(numeroProcedimiento);
        $("#altaUsuarioForm #altaUsuarioRole").val(altaUsuarioRole);
        $("#altaUsuarioForm").submit();
    }
}

function showDialogEdit(user) {
    tableDir3.ajax.url("usuariosListaFromNif?user=" + user).load();

    $('#nifedit').attr('value', user);
    $('#nifedit').attr('readonly', true);

    setTimeout(function() {

        var divData = $('#init-modal-modificar-usuario');
        var data = divData.data();

        data.data = divData.html();
        $mf.my_dialog.appendDialog(data, true, true);

        $(".mf-dialog #organos").autocomplete({
            source : 'autocomplete/dir3Vigentes',
            // autoFocus: true,
            focus : function(event, ui) {
                $(".mf-dialog #organos").val(ui.item.key);
                event.preventDefault();
            },
            select : function(event, ui) {
                $(".mf-dialog #organos").val(ui.item.key);
                event.preventDefault();
            }
        }).data("ui-autocomplete")._renderItem = function(ul, item) {
            return $("<li>").append("<a>" + item.key + " - " + item.value + "</a>").appendTo(ul);
        };

        $(".mf-dialog #procedimientos").autocomplete({
            source : 'autocomplete/numeroProcedimiento',
            // autoFocus: true,
            focus : function(event, ui) {
                $(".mf-dialog #procedimientos").val(ui.item.key);
                event.preventDefault();
            },
            select : function(event, ui) {
                $(".mf-dialog #procedimientos").val(ui.item.key);
                event.preventDefault();
            }
        }).data("ui-autocomplete")._renderItem = function(ul, item) {
            return $("<li>").append("<a>" + item.key + "</a>").appendTo(ul);
        };

        $("ul[id *= ui-id-]").css('zIndex', 9999);
    }, 1000);

}

function AsociarDir3Usuario() {
    var user = $('.mf-dialog [name=nifedit]').val();
    var codUnidadOrganica;
    var numeroProcedimiento;
    var activo;

    tableDir3.rows().eq(0).each(function(index) {
        var row = tableDir3.row(index);

        var data = row.data();

        if ($(".mf-dialog #id_check_" + data.codigo).prop("checked")) {
            codUnidadOrganica = data.codigo;
            numeroProcedimiento = data.numeroProcedimiento;
            activo = true;
        }

    });

    if (user != "") {
        $("#modificacionActivoForm #identificador").val(user);
        $("#modificacionActivoForm #codigoUnidadOrganica").val(codUnidadOrganica);
        $("#modificacionActivoForm #numeroProcedimiento").val(numeroProcedimiento);
        $("#modificacionActivoForm #activo").val(activo);
        $("#modificacionActivoForm").submit();
    }

    $mf.my_dialog.close_dialog();
}

function addOrgano() {
    var user = $('.mf-dialog [name=nifedit]').val();
    var codUnidadOrganica = $('.mf-dialog [name=organos]').val();
    var numeroProcedimiento = $('.mf-dialog [name=procedimientos]').val();
    var activo = false;

    if (user != "" && codUnidadOrganica != "") {
        $("#modificacionActivoForm #identificador").val(user);
        $("#modificacionActivoForm #codigoUnidadOrganica").val(codUnidadOrganica);
        $("#modificacionActivoForm #numeroProcedimiento").val(numeroProcedimiento);
        $("#modificacionActivoForm #activo").val(activo);
        $("#modificacionActivoForm").submit();
    } else {
        alert('No has introducido el campo Nuevo Dir3');
    }

    $mf.my_dialog.close_dialog();

}

function borrarUnidad() {

    var user = $('.mf-dialog [name=nifedit]').val();
    var codUnidadOrganica = "";
    var numeroProcedimiento = "";

    tableDir3.rows().eq(0).each(function(index) {
        var row = tableDir3.row(index);

        var data = row.data();

        if ($(".mf-dialog #id_check_" + data.codigo).prop("checked") && !data.activo) {
            codUnidadOrganica = data.codigo;
            numeroProcedimiento = data.numeroProcedimiento;
        }

    });

    if (user != "" && codUnidadOrganica != "") {
        $("#borrarUnidadForm #identificador").val(user);
        $("#borrarUnidadForm #codigoUnidadOrganica").val(codUnidadOrganica);
        $("#borrarUnidadForm #numeroProcedimiento").val(numeroProcedimiento);
        $("#borrarUnidadForm").submit();
    } else {
        alert('Solamente se puede borrar unidades desactivadas');
    }

}
