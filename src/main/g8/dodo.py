import logging
from doit.action import CmdAction

# pip install appscript
# import appscript
# from applescript import tell


DOIT_CONFIG = {'default_tasks': [], 'verbosity': 2}
LOGGER = logging.getLogger(__name__)


def task_stop_dev():
    return {'actions': [CmdAction("docker-compose down")]}


def task_start_dev():
    return {'actions': [CmdAction("docker-compose up --remove-orphans -d")]}
    # 'task_dep': ['stop_dev']


def task_sbtclean():
    return {'actions': [CmdAction("./sbt -sbt-version 1.4.2 -scala-version 2.13.0 clean")]}
