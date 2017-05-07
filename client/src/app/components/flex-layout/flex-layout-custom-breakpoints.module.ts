import { NgModule } from '@angular/core';
import { BREAKPOINTS, DEFAULT_BREAKPOINTS, BreakPoint } from '@angular/flex-layout'
import { validateSuffixes } from '@angular/flex-layout/utils';

export function customizeBreakPoints() {
	let breakpoints:BreakPoint[] = [
		...DEFAULT_BREAKPOINTS.map((it:BreakPoint) => {
			switch(it.alias) {
				case 'xs': it.mediaQuery = '(max-width: 959px)'; break;
				case 'sm': it.mediaQuery = '(min-width: 960px) and (max-width: 1279px)'; break;
				case 'md': it.mediaQuery = '(min-width: 1280px) and (max-width: 1599px)'; break;
				case 'lg': it.mediaQuery = '(min-width: 1600px) and (max-width: 1919px)'; break;
				case 'xl': it.mediaQuery = '(min-width: 1920px) and (max-width: 2559px)'; break;
				case 'lt-sm': it.mediaQuery = '(max-width: 959px)'; break;
				case 'lt-md': it.mediaQuery = '(max-width: 1279px)'; break;
				case 'lt-lg': it.mediaQuery = '(max-width: 1599px)'; break;
				case 'lt-xl': it.mediaQuery = '(max-width: 1919px)'; break;
				case 'gt-xs': it.mediaQuery = '(min-width: 960px)'; break;
				case 'gt-sm': it.mediaQuery = '(min-width: 1280px)'; break;
				case 'gt-md': it.mediaQuery = '(min-width: 1600px)'; break;
				case 'gt-lg': it.mediaQuery = '(min-width: 1920px)'; break;
			}
			return it;
		}),
		// The extra alias below currently do not work.
		{
			alias: 'xxl',
			mediaQuery: '(min-width: 2560px) and (max-width: 7680px)'
		},
		{
			alias: 'lt-xxl',
			mediaQuery: '(max-width: 2559px)'
		},
		{
			alias: 'gt-xl',
			mediaQuery: '(min-width: 2560px)'
		}
	];
	return validateSuffixes(breakpoints);
}

@NgModule({
	providers: [
		{
			provide: BREAKPOINTS,
			useFactory: customizeBreakPoints
		}
	]
})
export class FlexLayoutCustomBreakpointsModule {

}