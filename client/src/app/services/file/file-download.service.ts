import { saveAs } from 'file-saver';
import { AuthHttp } from 'angular2-jwt';
import { Response, ResponseContentType } from '@angular/http';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable()
export class FileDownloadService {

	readonly baseUrl:string = environment.resourceUrl;

	constructor(private authHttp: AuthHttp) {
		
	}

	downloadMobileData() {
		this.authHttp.get(this.baseUrl + "/mobile/download", {responseType: ResponseContentType.Blob})
			.subscribe((res) => {
				this.processBlob(res);
			});
	}

	processBlob(res) {
		let contentType:string = res.headers.get("Content-Type");
		let contentDisposition:string = res.headers.get("Content-Disposition");
		let display:string = this.getContentDisplay(contentDisposition);
		let filename:string = this.getFilename(contentDisposition);
		let blob:Blob = new Blob([res.blob()], {type: contentType});
		if (display == "inline") {
			// display file inline
		}
		else {
			saveAs(blob, filename);
		}
	}

	private getContentDisplay(contentDisposition:string):string {
		if (contentDisposition) {
			if (contentDisposition.startsWith("inline")) {
				return "inline";
			}
			if (contentDisposition.startsWith("attachment")) {
				return "attachment";
			}
			if (contentDisposition.startsWith("form-data")) {
				return "form-data";
			}
		}
		return "unknown";
	}

	private getFilename(contentDisposition:string):string {
		let idx = contentDisposition.indexOf("filename");
		if (idx > -1) {
			return contentDisposition.substr(idx + 9).replace(/\"/g, "");
		}
		return null;
	}

}