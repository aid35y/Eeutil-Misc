$(document).ready(function() {
    $('#js-check-metadatos').change(function(e) {
        var $el = $(e.currentTarget);
        var classSi;
        var classNo;
        if ($el.is(':checked')) {
            classSi = 'showed';
            classNo = 'hidden';
        } else {
            classNo = 'showed';
            classSi = 'hidden';
        }
        $('#js-div-metadatos').removeClass(classNo).addClass(classSi);
    });
});

function addMetadatoExp(metadatoName, metadatoValue) {
    if ($('#keyMetadatoAdicional').val() && $('#valueMetadatoAdicional').val()) {
        if (typeof (metadatosAdded) === 'undefined') {
            window.metadatosAdded = 0;
        }
        //se añade un input key y otro input valor de metadato 
        var key_input = document.createElement("input");
        var value_input = document.createElement("input");
        var remove_button = document.createElement("button");
        var div = document.createElement("div");

        key_input.setAttribute("type", "text");
        key_input.setAttribute("id", "metadatosAdicionales.metadatoAdicional" + metadatosAdded + ".nombre");
        key_input.setAttribute("name", "metadatosAdicionales.metadatoAdicional[" + metadatosAdded + "].nombre");
        key_input.setAttribute("class", "sticked-input-right");
        key_input.setAttribute("disabled", "disabled");
        key_input.setAttribute("value", $('#keyMetadatoAdicional').val());
        value_input.setAttribute("type", "text");
        value_input.setAttribute("name", "metadatosAdicionales.metadatoAdicional[" + metadatosAdded + "].valor");
        value_input.setAttribute("id", "metadatosAdicionales.metadatoAdicional" + metadatosAdded + ".valor");
        value_input.setAttribute("class", "sticked-input-right");
        value_input.setAttribute("disabled", "disabled");
        value_input.setAttribute("value", $('#valueMetadatoAdicional').val());
        remove_button.setAttribute("type", "button");
        remove_button.setAttribute("name", "button");
        remove_button.setAttribute("class", "mf-icon mf-icon-cancel2");
        remove_button.setAttribute("id", "removeMetadatoButton_" + metadatosAdded);
        remove_button.setAttribute("value", "Eliminar");
        remove_button.setAttribute("onclick", "removeMetadatoExp(" + metadatosAdded + ")");
        div.setAttribute("id", "divMetadatoAdicional_" + metadatosAdded);

        $("#listMetadatosAdicionales").append(div);

        $("#divMetadatoAdicional_" + metadatosAdded).append(key_input);
        $("#divMetadatoAdicional_" + metadatosAdded).append(value_input);
        $("#divMetadatoAdicional_" + metadatosAdded).append(remove_button);

        $('#keyMetadatoAdicional').val('');
        $('#valueMetadatoAdicional').val('');
        metadatosAdded++;
    }
}

function removeMetadatoExp(position) {
    $("#metadatosAdicionales.metadatoAdicional" + position + ".nombre").remove();
    $("#metadatosAdicionales.metadatoAdicional" + position + ".valor").remove();
    $("#removeMetadatoButton_" + position).remove();
    $("#divMetadatoAdicional_" + position).remove();

    metadatosAdded--;
}
