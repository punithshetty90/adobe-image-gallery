$.widget( "myproject.gallerySection", {
    options: {},

    init: function (params) {
    	let self = this;
    	self.options = $.extend({}, options, params);
    },
    
    _create: function() {
        let self = this;
        self.startIndex = 0;

        /* Compile templates */
       self.galleryTemplate = Handlebars.compile(window.AdobeCoding.templates.galleryTemplate);
       
        self.doAjax(function (err) {
            if (err) {
                return console.log('AJAX Error');
            }
            
        });

        /* Load More Listeners*/
        self.element.on('click', '#load-more button', function () {
            self.doAjax(function (err) {
                if (err) {
                    return console.log('AJAX Error');
                }
                
            });
        });
        
    },

    doAjax: function (callback) {
        let self = this;
        console.log(self.startIndex);
        $.ajax({
            url: "/apps/myproject/getImages",
            data: {
                offset: self.startIndex
            },
            type: "get",
            success: function (res) {
                let json = JSON.parse(res);
                self.startIndex = json.hitsPerPage + json.offset;
                self.element.find('div.cards').append(self.galleryTemplate({ info: json.assets  }));

                if(self.startIndex < json.totalMatches){
                    self.element.find('#load-more').show();
                }else{
                    self.element.find('#load-more').hide();
                }
                
                callback(null);
            },
            error: function (err) {
                callback(err);
            },
            cache: false
        });

    },

    
});

$(document).ready(function () {
    if ($('[data-component="gallery-section"]').length > 0) {
        $('[data-component="gallery-section"]').each(function () {
            $(this).gallerySection();
        });
    }
});