
/**
 * @author Alvin Quach
 */
export class ArrayUtils { 

	/**
	 * Sorts an array of objects by the objects' specified properties.
	 * If more than one property is specified, then subsequent properties will only be used 
	 * if the previous properties yielded the same result for two compared objects.
	 * Nested properties should be period delimited (ie. parent.child1.child2).
	 * @param array The array of objects to be sorted.
	 * @param properties The array of object properties to sort the array by.
	 * @param caseSensitive Whether string comparisons should be case sensitive.
	 */
	static sort(array:any[], properties:string[], reversed:boolean, caseSensitive:boolean = false):any[] {
		array.sort((a, b) => {
			for (let i = 0; i < properties.length; i++) {
				let result:number = ArrayUtils.compare(a, b, properties[i], caseSensitive);
				if (result == 0) {
					continue;
				}
				return result * (reversed ? -1 : 1);
			}
			return 0;
		});
		return array;
	}

	private static compare(a:any, b:any, property:string, caseSensitive:boolean):number {
		let paths:string[] = property.split(".");
		let found:{a:boolean, b:boolean} = {
			a: false,
			b: false
		}
		for (let i = 0; i < paths.length; i++) {
			let path:string = paths[i];
			found.a = (a[path] != null);
			found.b = (b[path] != null);
			if (!found.a && !found.b) {
				return 0;
			}
			if (found.a) {
				if (!found.b) {
					return 1;
				}
				a = a[path];
			}
			if (found.b) {
				if (!found.a) {
					return -1;
				}
				b = b[path];
			}
		}
		if (!caseSensitive) {
			if (typeof a == "string") {
				a = a.toLowerCase();
			}
			if (typeof b == "string") {
				b = b.toLowerCase();
			}
		}
		return (a == b ? 0 : (a < b ? -1 : 1));
	}

}