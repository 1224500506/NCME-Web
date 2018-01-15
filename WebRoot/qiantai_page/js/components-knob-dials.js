var ComponentsKnobDials = function () {

    return {
        //main function to initiate the module
        
        init: function () {
            // general knob
            $(".knob").knob({
                'dynamicDraw': false,
                'thickness': 0.2,
                'tickColorizeValues': true,
                'skin': 'tron'
            });  
        }

    };

}();
