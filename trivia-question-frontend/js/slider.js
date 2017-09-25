
function Slider(maindiv, scope){
	var counter=0;
	var $current=$(maindiv+'>#p'+counter)
	$current.addClass('current');
	var animend=whichTransitionEvent();
	var numberOfPages=$(maindiv).children('.slide').length;
	var animEndEventNames = {
			'WebkitAnimation' : 'webkitAnimationEnd',
			'OAnimation' : 'oAnimationEnd',
			'msAnimation' : 'MSAnimationEnd',
			'animation' : 'animationend'
		},
		animEndEventName = animEndEventNames[ Modernizr.prefixed( 'animation' ) ]
	var isanim = false;
	/////////////////////////////////////////////////
	//icons
	for(var c = 0;c<numberOfPages;c++){
		$(maindiv+'>.icons>.iconsL').append("<li class='icon' id='i"+c+"'></li>");
	}
	$(maindiv+'>.icons>.iconsL>#i0').addClass('current');
	$(maindiv+'>.icons>.iconsL>.icon').click(function(){
		if(isanim)
			return;
		isanim=true;
		var iconid = $(this).attr('id');
		iconid=parseInt(iconid.substring(1));

		var $next=$(maindiv+'>#p'+iconid);
		if(iconid==counter){
			isanim=false;
			return;
		}
		else{
			$next.addClass('current');
			if(iconid>counter){
				$current.addClass('Next_SlideOut');
				$next.addClass('Next_SlideIn');
			}
			else{
				$current.addClass('Prev_SlideOut');
				$next.addClass('Prev_SlideIn');
			}
			$next.on(animEndEventName,function(){
				$current.removeClass('current');
				$next.off(animEndEventName);
				$current.removeClass('Prev_SlideOut')
				$next.removeClass('Prev_SlideIn')
				$current.removeClass('Next_SlideOut')
				$next.removeClass('Next_SlideIn')
				$(maindiv+'>.icons').trigger('iconpointer',[counter,iconid]);
				scope.$apply(function(){
					counter=iconid;
				})
				setButtons(counter,numberOfPages);
				isanim=false;
				$next.off(animEndEventName);
				$current=$next;
				
			}) 
		}

	})
	$(maindiv+'>.icons').on('iconpointer',function(event,param1,param2){
		$(maindiv+'>.icons>.iconsL>#i'+param2).addClass('current');
		$(maindiv+'>.icons>.iconsL>#i'+param1).removeClass('current');
	})
	////////////////////////////////////////////////////


	
function nextpagecounter(current){
	var numberOfPages=$(maindiv).children('.slide').length;
	return (((current+1)%numberOfPages)+numberOfPages)%numberOfPages;
}
function prevpagecounter(current){
	var numberOfPages=$(maindiv).children('.slide').length;
	return (((current-1)%numberOfPages)+numberOfPages)%numberOfPages;
}
function whichTransitionEvent(){
    var t;
    var el = document.createElement('fakeelement');
    var transitions = {
      'transition':'transitionend',
      'OTransition':'oTransitionEnd',
      'MozTransition':'transitionend',
      'WebkitTransition':'webkitTransitionEnd'
    }

    for(t in transitions){
        if( el.style[t] !== undefined ){
            return transitions[t];
        }
    }
}
function setButtons(counter,numberOfPages){
	if(counter==0){
		$(maindiv+'>.prevbutton>.prevbt').attr('disabled','disabled');
		$(maindiv+'>.prevbutton>.prevbt').attr('color','red');
	}else{
		$(maindiv+'>.prevbutton>.prevbt').removeAttr('disabled');
	}
	if(counter==numberOfPages-1){
		$(maindiv+'>.nextbutton>.nextbt').attr('disabled','disabled');
	}
	else{
		$(maindiv+'>.nextbutton>.nextbt').removeAttr('disabled');
	}
}

	return {
		'next': function(){
			if(isanim)
				return;
			var nextCounter = nextpagecounter(counter);
			if(nextCounter == 0)
				return;
			isanim=true;
			$next=$(maindiv+'>#p'+nextCounter);
			$current.addClass('Next_SlideOut').on(animEndEventName,function(){
				$current.off(animEndEventName)
				
			})

			$next.addClass('current');
			$next.addClass('Next_SlideIn');
			$next.on(animEndEventName,function(){
				$(maindiv+'>.icons').trigger('iconpointer',[nextCounter-1,nextCounter]);
				$current.removeClass('current');
				$current.off(animEndEventName);
				$next.off(animEndEventName);
				$current.removeClass('Next_SlideOut')
				$next.removeClass('Next_SlideIn')
				setButtons(nextCounter,numberOfPages);
				isanim=false;
				$next.off(animEndEventName);
				$current=$next;
				scope.$apply(function(){
					counter = nextCounter;
				})
			})
		},
		'previous': function(){
			if(isanim)
				return;
			isanim=true;

			var prevCounter = prevpagecounter(counter);
			$next=$(maindiv+'>#p'+prevCounter);
			$current.addClass('Prev_SlideOut').on(animEndEventName,function(){
				$current.off(animEndEventName)
				
			})

			$next.addClass('current');
			$next.addClass('Prev_SlideIn');
			$next.on(animEndEventName,function(){
				$(maindiv+'>.icons').trigger('iconpointer',[prevCounter+1,prevCounter]);
				$current.removeClass('current');
				$current.off(animEndEventName);
				$next.off(animEndEventName);
				$current.removeClass('Prev_SlideOut')
				$next.removeClass('Prev_SlideIn')
				setButtons(prevCounter,numberOfPages);
				isanim=false;
				$next.off(animEndEventName);
				$current=$next;
				scope.$apply(function(){
					counter = prevCounter;
				})
				

			})
		},
		'allowPrevButton': function(){
			return counter != 0;
		},
		'allowNextButton': function(){
			return counter != numberOfPages-1;
		}

	}
}