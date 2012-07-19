$(document).ready(function () {                

        //Lorsqu'on entre dans le menu
        $('ul.menu_deroulant li').mouseenter(function(){
            $(this).find('ul:first').slideDown();                                        
        });        
        
        // Lorsqu'on sort du menu
        $('ul.menu_deroulant li').mouseleave(function(){            
            $(this).find('ul:first').slideUp();                    
        });    

});