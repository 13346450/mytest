package com.dnake.mapper.system;
import java.util.List;
import java.util.Map;
import com.dnake.domain.common.Page;
import com.dnake.domain.system.SysLogoSettings;
public interface SysLogoSettingsMapper {
void insert(SysLogoSettings sysLogoSettings);

void delete(long idKey);

void update(SysLogoSettings sysLogoSettings);

SysLogoSettings queryByIdkey(long idKey);

void deleteMulti(Map<String, Object> map);

List<SysLogoSettings> queryPage(Page<SysLogoSettings> page);

void insertMulti(List<SysLogoSettings> list);

List<SysLogoSettings> queryAll(Map<String,Object> map);

}
