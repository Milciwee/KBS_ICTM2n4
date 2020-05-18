import paramiko
from time import sleep

ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
try:
  ssh.connect(hostname='192.168.1.2', username='root', password='Teamsvmware01!', port=3389)
  print("Connected")
except Exception as error:
  print(error)

stdin, stdout, stderr = ssh.exec_command("systemctl reboot")
ssh.close()
print("Server rebooted")