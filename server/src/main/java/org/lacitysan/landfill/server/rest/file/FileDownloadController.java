package org.lacitysan.landfill.server.rest.file;

import org.lacitysan.landfill.server.config.constant.ApplicationProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationProperty.RESOURCE_PATH + "/download")
@RestController
public class FileDownloadController {

}
