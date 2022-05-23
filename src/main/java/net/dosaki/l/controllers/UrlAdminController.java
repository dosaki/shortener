package net.dosaki.l.controllers;

import net.dosaki.l.controllers.dto.ShortUrlDTO;
import net.dosaki.l.controllers.dto.TokenDTO;
import java.util.List;

public interface UrlAdminController {
    public TokenDTO token();
    public List<ShortUrlDTO> list();
}
