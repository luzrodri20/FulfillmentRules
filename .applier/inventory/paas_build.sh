export ANSIBLE_CONFIG=ansible.cfg

ansible-galaxy install -r .applier/inventory/requirements.yml --roles-path=galaxy
ansible-playbook -i .applier/inventory/ galaxy/openshift-applier/playbooks/openshift-cluster-seed.yml -e filter_tags=build-pipeline

echo "Navigate to the OpenShift Console and watch your deployment."

