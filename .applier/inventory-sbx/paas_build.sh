export ANSIBLE_CONFIG=ansible.cfg

ansible-galaxy install -r .applier/inventory-sbx/requirements.yml --roles-path=galaxy
ansible-playbook -i .applier/inventory-sbx/ galaxy/openshift-applier/playbooks/openshift-cluster-seed.yml -e filter_tags=sbx

echo "Navigate to the OpenShift Console and watch your deployment."

