According to official docs...

### @Component

> But instead of us writing the implementation, we can annotate it with @Component
> to have Dagger generate an implementation for us.

> @Component tells Dagger to implement an interface or abstract class
> that creates and returns one or more application objects.
> Dagger will generate a class that implements the component type.
> The generated type will be named DaggerYourType (or DaggerYourType_NestedType for nested types)

- Responsible for providing dependencies to other classes.
- Just a simple way to implement factory pattern.


### @Inject

> The @Inject annotation indicates to Dagger that when we ask for a CommandRouter,
> Dagger should call new CommandRouter().

> @Inject on a constructor tells Dagger how to instantiate that class. We’ll see more shortly.

> Parameters to an @Inject constructor are the dependencies of the class.
> Dagger will provide a class’s dependencies to instantiate the class itself.
> Note that this is recursive: a dependency may have dependencies of its own!

- Inject is a connection between user of the dependency, and the provider - Component.
- Inject is an edge between nodes in dependency graph.
- Dependency resolving (instantiating the class) process is recursive.


### @Binds

> This @Binds method tells Dagger that when something depends on a Command,
> Dagger should provide a HelloWorldCommand object in its place.

> @Binds methods are one way to tell Dagger how to construct an instance.
> They are abstract methods on modules that associate one type that
> Dagger already knows how to construct (the method’s parameter)
> with a type that Dagger doesn’t yet know how to construct (the method’s return type).

- It tells Dagger how to construct an instance.
- It "binds" the parameter type and the return type - when someone request return type,
  then it provides an instance of return type, automatically.
  There should be an instruction on how to build an instance of return type, of course,
  something like @Inject constructor or @Provides
- Binds must be inside a module.


### @Provides

> A @Provides method works a lot like an @Inject constructor:
> here it tells Dagger that when it needs an instance of Outputter,
> it should call SystemOutModule.textOutputter() to get one.

> @Provides methods can contain arbitrary code as long as they return an instance of the provided type.
> They do not need to create a new instance on each invocation.

- It tells Dagger how to construct an instance.
- It tells Dagger that when something requests an instance of the type the method returns, it should call that method to get an instance.
- @Provides works very similar to @Inject constructor


### @Modules

> Modules are collections of binding methods
> that give Dagger instructions on how to provide instances.

> @Modules are classes or interfaces that act as collections of instructions for Dagger
> on how to construct dependencies. They’re called modules because they are modular:
> you can mix and match modules in different applications and contexts.

- Modules are classes/interfaces that give us some instructions on how to construct dependencies.
- Modules are modular, so you can mix them in different applications.