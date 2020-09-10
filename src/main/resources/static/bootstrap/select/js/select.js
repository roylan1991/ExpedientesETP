"use strict";

function LoadSelect2Script(callback) {
    if (!$.fn.select2) {
        $.getScript('bootstrap/select/plugins/select2.min.js', callback);
    } else {
        if (callback && typeof (callback) === "function") {
            callback();
        }
    }
}

