package com.lma.dao;

import java.util.List;

import com.lma.model.Site;

public interface SiteDAO {

    boolean addSite(Site site);

    boolean updateSite(Site site);

    boolean deleteSite(int siteId);

    Site getSiteById(int siteId);

    List<Site> getAllSites();

    List<Site> getSitesByOwner(int ownerId);

//    Site getSiteByOwnerId(int ownerId);
}
