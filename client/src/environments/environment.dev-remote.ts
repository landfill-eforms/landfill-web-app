import { AppConstant } from './../app/app.constant';

const ResourceServerUrl: string = "http://landfill-dev-server.us-west-1.elasticbeanstalk.com/";

export const environment = {
  production: true,
  loginUrl: ResourceServerUrl + "/login",
  resourceUrl: ResourceServerUrl + "/rest",
  assetsUrl: "/assets"
};