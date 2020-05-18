import paramiko
import time
from time import sleep

ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
try:
  ssh.connect(hostname='192.168.1.2', username='root', password='Teamsvmware01!', port=3389)
  print("Connected")
except Exception as error:
  print(error)

print("Stressing CPU")
stdin, stdout, stderr = ssh.exec_command("sudo stress --cpu  8 --timeout 20")
command_result = stdout.read().decode('ascii').strip("\n")
ssh.close()
print("Stresstest on CPU completed")