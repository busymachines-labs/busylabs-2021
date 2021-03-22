# busylabs 2021

## setup

- It is highly recommended that you run some kind of linux, or macOS. You can program Scala and install all tools listed here on Windows, BUT:
  - setting up docker environment on windows can be a bit of a pain
  - none of us really have Windows experience, so we can't help you debug setup problems as easily. Ideally you'd do a dual boot windows/ubuntu installation if you're running windows. It's not recommended to run a VM w/ ubuntu because development will be slow as hell, and you'd need at least 4GB of RAM just for the VM.
  - eventually you have to learn

- Install java JDK. At least version 8.

  - option 1: install [sdkman.io](https://sdkman.io/install). And simply manage your java versions, by installing via the terminal command: `sdk install java 8.0.282.hs-adpt`
  - option 2: manually install java JDK from [https://adoptopenjdk.net/installation.html#](https://adoptopenjdk.net/installation.html#)

- Install sbt

  - option 1: let `sdkman` install sbt for you via the terminal command `sdk install sbt`
  - option 2: manually install [sbt](https://www.scala-sbt.org/1.x/docs/Setup.html)

- Install [docker](https://docs.docker.com/engine/install/)

  - if you are on windows, maybe try out [WSL](https://docs.microsoft.com/en-us/windows/wsl/install-win10)

- Install [git](https://git-scm.com/downloads)
  - optionally, you can configure your git w/ your [github](https://docs.github.com/en/github/authenticating-to-github/connecting-to-github-with-ssh) account if you have one.

## IDEs

1. [IntelliJ IDEA](https://www.jetbrains.com/idea/download) Community Edition (the ultimate edition brings nothing to us)
   - make sure you install the Scala plugin. If this is the first time installing IntelliJ then you will be prompted w/ a wizard that displays "most popular plugins", and Scala is the first one of them. Otherwise follow the instructions to [install plugins](https://www.jetbrains.com/help/idea/managing-plugins.html) and search for the Scala one. There's only one.
   - once installed you can go to `File` -> `New Project` -> `From Existing Sources` and select any folder that has a `.build.sbt` file in it to import it as a "sbt project".
2. [Visual Studio Code](https://code.visualstudio.com/) with the [metals plugin](https://scalameta.org/metals/docs/editors/vscode.html).
   - Just focus on the "Importing build" section. Anything else is not relevant for now.

## For starters

1. [clone](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository) this repository. 
2. import it into the IDE of your choice. Run `Main.scala` (look up how to search for files in your IDE).
3. try compiling from the command line with `sbt` and then running `run` in the REPL to achieve the same result