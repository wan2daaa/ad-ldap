## AD Powershell script 
[MS AD WindowsServer2022](https://learn.microsoft.com/en-us/powershell/module/activedirectory/?view=windowsserver2022-ps)

- 유저 찾기
```shell
GET-ADUser -Identity Administrator
```

- base DN 찾기
```shell
(Get-ADDomain).DistinguishedName
```


- LDAP attribute List

[attribute list](https://documentation.sailpoint.com/connectors/active_directory/help/integrating_active_directory/ldap_names.html)

- AD UserAccountControl
[AD UserAccountControl properties](https://learn.microsoft.com/en-us/troubleshoot/windows-server/active-directory/useraccountcontrol-manipulate-account-properties)

- LDAP account type
[LDAP account type](https://www.ldap-account-manager.org/lamcms/supportedTypes)