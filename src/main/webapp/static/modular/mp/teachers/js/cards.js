var Cards = (function() {
	
	var view 	= $('.view');
	var vw 		= view.innerWidth();
	var vh	 	= view.innerHeight();
	var vo 		= view.offset();
	var card 	= $('.card__item');
	var cardfull = $('.card__full');
	var cardfulltop = cardfull.find('.card__full-top');
	var arrow = cardfulltop.find('svg');
	var cardnum = cardfulltop.find('.card__full-num');
	var cardhandle = cardfull.find('.card__full-handle');
	var cardinfo = cardfull.find('.card__full-info');
	var w 		= $(window);
	
	var data = [
		{
			num: 1,
			handle: '1.杨基歌',
			info: 'http://www.bakclass.com/rsc/4643'
		},
		{
			num: 2,
			handle: '2.交城山',
			info: 'http://www.bakclass.com/rsc/4644'
		},
		{
			num: 3,
			handle: '3.小白菜',
			info: 'http://www.bakclass.com/rsc/4645'
		},
		{
			num: 4,
			handle: '4.玛丽的小羊羔',
			info: 'http://www.bakclass.com/rsc/4646'
		},
		{
			num: 5,
			handle: '5.粉刷匠',
			info: 'http://www.bakclass.com/rsc/4647'
		},
		{
			num: 6,
			handle: '6.划船',
			info: 'http://www.bakclass.com/rsc/4648'
		}
	];

	
	var moveCard = function() {
		var self = $(this);
		var selfIndex = self.index();
		var selfO = self.offset();
		var ty = w.innerHeight()/2 - selfO.top -4;
		
		var color = self.css('border-top-color');
		cardfulltop.css('background-color', color);
		cardhandle.css('color', color);
		
		updateData(selfIndex);
		
		self.css({
			'transform': 'translateY(' + ty + 'px)'
		});
				
		self.on('transitionend', function() {
			cardfull.addClass('active');
			self.off('transitionend');
		});
		
		return false;
	};
	
	var closeCard = function() {
		cardfull.removeClass('active');
		cardnum.hide();
		cardinfo.hide();
		cardhandle.hide();
		cardfull.on('transitionend', function() {
			card.removeAttr('style');
			cardnum.show();
			cardinfo.show();
			cardhandle.show();
			cardfull.off('transitionend');
		});
	};
	
	var updateData = function(index) {
		cardnum.text(data[index].num);
		cardhandle.text(data[index].handle);
		cardinfo.text(data[index].info);
	};
	
	var bindActions = function() {
		card.on('click', moveCard);
		arrow.on('click', closeCard);
	};
	
	var init = function() {
		bindActions();
	};
	
	return {
		init: init
	};
	
}());

Cards.init();