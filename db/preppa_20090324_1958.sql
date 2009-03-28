-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51a-24


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema preppaco_tapestry
--

CREATE DATABASE IF NOT EXISTS preppaco_tapestry;
USE preppaco_tapestry;

--
-- Definition of table `preppaco_tapestry`.`adminconfigs`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`adminconfigs`;
CREATE TABLE  `preppaco_tapestry`.`adminconfigs` (
  `id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`adminconfigs`
--

/*!40000 ALTER TABLE `adminconfigs` DISABLE KEYS */;
LOCK TABLES `adminconfigs` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `adminconfigs` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`articles`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`articles`;
CREATE TABLE  `preppaco_tapestry`.`articles` (
  `id` int(11) NOT NULL,
  `title` varchar(255) default NULL,
  `body` text,
  `testsubject_id` int(11) default NULL,
  `sources` text,
  `tags` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`articles`
--

/*!40000 ALTER TABLE `articles` DISABLE KEYS */;
LOCK TABLES `articles` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `articles` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`essaysquestions`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`essaysquestions`;
CREATE TABLE  `preppaco_tapestry`.`essaysquestions` (
  `id` int(11) NOT NULL,
  `question` text,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`essaysquestions`
--

/*!40000 ALTER TABLE `essaysquestions` DISABLE KEYS */;
LOCK TABLES `essaysquestions` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `essaysquestions` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`improvingparagraphs`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`improvingparagraphs`;
CREATE TABLE  `preppaco_tapestry`.`improvingparagraphs` (
  `id` int(11) NOT NULL,
  `passage1` text,
  `source` text,
  `tags` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`improvingparagraphs`
--

/*!40000 ALTER TABLE `improvingparagraphs` DISABLE KEYS */;
LOCK TABLES `improvingparagraphs` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `improvingparagraphs` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`mathgridins`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`mathgridins`;
CREATE TABLE  `preppaco_tapestry`.`mathgridins` (
  `id` int(11) NOT NULL,
  `question` text,
  `range` tinyint(4) default NULL,
  `answerlow` varchar(255) default NULL,
  `answerhigh` varchar(255) default NULL,
  `singleanswer` varchar(255) default NULL,
  `explanation` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`mathgridins`
--

/*!40000 ALTER TABLE `mathgridins` DISABLE KEYS */;
LOCK TABLES `mathgridins` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `mathgridins` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`mathmulitplechoice`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`mathmulitplechoice`;
CREATE TABLE  `preppaco_tapestry`.`mathmulitplechoice` (
  `id` bit(1) NOT NULL,
  `imagelink` varchar(255) default NULL,
  `tags` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`mathmulitplechoice`
--

/*!40000 ALTER TABLE `mathmulitplechoice` DISABLE KEYS */;
LOCK TABLES `mathmulitplechoice` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `mathmulitplechoice` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`passageslong`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`passageslong`;
CREATE TABLE  `preppaco_tapestry`.`passageslong` (
  `id` int(11) NOT NULL,
  `introduction` text,
  `source` text,
  `tags` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`passageslong`
--

/*!40000 ALTER TABLE `passageslong` DISABLE KEYS */;
LOCK TABLES `passageslong` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `passageslong` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`passageslongdual`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`passageslongdual`;
CREATE TABLE  `preppaco_tapestry`.`passageslongdual` (
  `id` int(11) NOT NULL,
  `introduction` text,
  `passage1` text,
  `passage2` text,
  `source` text,
  `tags` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`passageslongdual`
--

/*!40000 ALTER TABLE `passageslongdual` DISABLE KEYS */;
LOCK TABLES `passageslongdual` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `passageslongdual` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`passagesshort`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`passagesshort`;
CREATE TABLE  `preppaco_tapestry`.`passagesshort` (
  `id` int(11) NOT NULL,
  `body` text,
  `source` text,
  `tags` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`passagesshort`
--

/*!40000 ALTER TABLE `passagesshort` DISABLE KEYS */;
LOCK TABLES `passagesshort` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `passagesshort` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`passagesshortdual`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`passagesshortdual`;
CREATE TABLE  `preppaco_tapestry`.`passagesshortdual` (
  `id` int(11) NOT NULL,
  `passage1` text,
  `passage2` text,
  `source` text,
  `tags` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`passagesshortdual`
--

/*!40000 ALTER TABLE `passagesshortdual` DISABLE KEYS */;
LOCK TABLES `passagesshortdual` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `passagesshortdual` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`profiles`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`profiles`;
CREATE TABLE  `preppaco_tapestry`.`profiles` (
  `id` int(11) NOT NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`profiles`
--

/*!40000 ALTER TABLE `profiles` DISABLE KEYS */;
LOCK TABLES `profiles` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `profiles` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`questionanswers`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`questionanswers`;
CREATE TABLE  `preppaco_tapestry`.`questionanswers` (
  `id` int(11) NOT NULL,
  `question_id` int(11) default NULL,
  `correct` tinyint(4) default NULL,
  `body` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`questionanswers`
--

/*!40000 ALTER TABLE `questionanswers` DISABLE KEYS */;
LOCK TABLES `questionanswers` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `questionanswers` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`questions`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`questions`;
CREATE TABLE  `preppaco_tapestry`.`questions` (
  `id` int(11) NOT NULL,
  `questiontype_id` int(11) default NULL,
  `question` text,
  `tags` text,
  `source` text,
  `explanation` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  `question_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`questions`
--

/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
LOCK TABLES `questions` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`questiontypes`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`questiontypes`;
CREATE TABLE  `preppaco_tapestry`.`questiontypes` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `testsubject_id` int(11) NOT NULL,
  `sources` text,
  `tags` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`questiontypes`
--

/*!40000 ALTER TABLE `questiontypes` DISABLE KEYS */;
LOCK TABLES `questiontypes` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `questiontypes` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`testsubjects`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`testsubjects`;
CREATE TABLE  `preppaco_tapestry`.`testsubjects` (
  `id` int(11) NOT NULL,
  `name` varchar(255) default NULL,
  `description` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`testsubjects`
--

/*!40000 ALTER TABLE `testsubjects` DISABLE KEYS */;
LOCK TABLES `testsubjects` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `testsubjects` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`users`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`users`;
CREATE TABLE  `preppaco_tapestry`.`users` (
  `id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
LOCK TABLES `users` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`vocabs`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`vocabs`;
CREATE TABLE  `preppaco_tapestry`.`vocabs` (
  `id` int(11) NOT NULL,
  `name` varchar(255) default NULL,
  `partofspeech` varchar(255) default NULL,
  `definition` text,
  `tags` text,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`vocabs`
--

/*!40000 ALTER TABLE `vocabs` DISABLE KEYS */;
LOCK TABLES `vocabs` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `vocabs` ENABLE KEYS */;


--
-- Definition of table `preppaco_tapestry`.`vocabssentences`
--

DROP TABLE IF EXISTS `preppaco_tapestry`.`vocabssentences`;
CREATE TABLE  `preppaco_tapestry`.`vocabssentences` (
  `id` int(11) NOT NULL,
  `sentence` varchar(255) default NULL,
  `vocab_id` int(11) default NULL,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL default '0000-00-00 00:00:00',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `preppaco_tapestry`.`vocabssentences`
--

/*!40000 ALTER TABLE `vocabssentences` DISABLE KEYS */;
LOCK TABLES `vocabssentences` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `vocabssentences` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
