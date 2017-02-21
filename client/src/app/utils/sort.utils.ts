
/**
 * @author Alvin Quach
 */
export class SortUtils {

	/**
	 * Sorts an array of objects by the objects' specified properties.
	 * If more than one property is specified, then subsequent properties will only be used 
	 * if the previous properties yielded the same result for two compared objects.
	 * Nested properties should be period delimited (ie. parent.child1.child2).
	 * Also updates the current sort settings.
	 * @param sort The current sort settings.
	 * @param sortBy The string identifier of how the data is to be sorted. Can be arbitrary.
	 * @param data The array of objects to be sorted.
	 * @param properties The array of object properties to sort the array by.
	 * @param caseSensitive Whether string comparisons should be case sensitive.
	 */
	static sortAndUpdate(sort:Sort, sortBy:string, data:any[], properties:string[], caseSensitive:boolean = false):any[] {
		if (sort.current == sortBy) {
			sort.reversed = !sort.reversed;
		}
		else {
			sort.current = sortBy;
			sort.reversed = false;
		}
		return SortUtils.sort(data, properties, sort.reversed, caseSensitive);
	}

	/**
	 * Sorts an array of objects by the objects' specified properties.
	 * If more than one property is specified, then subsequent properties will only be used 
	 * if the previous properties yielded the same result for two compared objects.
	 * Nested properties should be period delimited (ie. parent.child1.child2).
	 * @param data The array of objects to be sorted.
	 * @param properties The array of object properties to sort the array by.
	 * @param caseSensitive Whether string comparisons should be case sensitive.
	 */
	static sort(data:any[], properties:string[], reversed:boolean, caseSensitive:boolean = false):any[] {
		if (properties) {
			data.sort((a, b) => {
				for (let i = 0; i < properties.length; i++) {
					let result:number = SortUtils.compare(a, b, properties[i], caseSensitive);
					if (result == 0) {
						continue;
					}
					return result * (reversed ? -1 : 1);
				}
				return 0;
			});
		}
		return data;
	}

	/** Helper method for comparing two objects by a property. */
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

/** Sort settings. */
export class Sort {

	/** A string identifier of how the data is currently sorted. Can be arbitrary. */
	current:string;

	/** Whether the sorting order is reversed. */
	reversed:boolean;

}