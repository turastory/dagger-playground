According to official docs...

### @Component

> But instead of us writing the implementation, we can annotate it with @Component
> to have Dagger generate an implementation for us.

> @Component tells Dagger to implement an interface or abstract class
> that creates and returns one or more application objects.
> Dagger will generate a class that implements the component type.
> The generated type will be named DaggerYourType (or DaggerYourType_NestedType for nested types)

About Component..

- Responsible for providing dependencies to other classes.
- Just a simple way to implement factory pattern.


### @Inject

> The @Inject annotation indicates to Dagger that when we ask for a CommandRouter,
> Dagger should call new CommandRouter().

> @Inject on a constructor tells Dagger how to instantiate that class. We’ll see more shortly.

> Parameters to an @Inject constructor are the dependencies of the class.
> Dagger will provide a class’s dependencies to instantiate the class itself.
> Note that this is recursive: a dependency may have dependencies of its own!

About Inject..

- Inject is a connection between user of the dependency, and the provider - Component.
- Inject is an edge between nodes in dependency graph.
- Dependency resolving (instantiating the class) process is recursive.
