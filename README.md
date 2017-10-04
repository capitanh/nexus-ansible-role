Nexus Ansible Role
=========

This role installs sonatype nexus

Requirements
------------

None

Role Variables
--------------

This role requires the following variables to be defined elsewhere in the playbook that uses it:
```yaml
nexus_version:      3.6.0-02
nexus_user:         nexus
nexus_home:         /var/lib/nexus
nexus_port:         8081
```

Dependencies
------------

None

Example Playbook
----------------

Register the role in requirements.yml:
```yaml
- src: capitanh.nexus-ansible-role
  name: nexus
```
Include it in your playbooks:
```yaml
- hosts: servers
  roles:
    - nexus
```

License
-------

BSD
